package com.jsu.lei.comment;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;

import net.sf.json.JSONObject;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import org.springframework.stereotype.Controller;

import com.jsu.lei.util.ConfigUtils;
import com.jsu.lei.util.Encrypter;
import com.jsu.lei.vo.FileAttr;
import com.jsu.lei.vo.FileConf;

@Controller
@Path("/")
public class UploadAction {
	private static Logger logger = Logger.getLogger(UploadAction.class);

	public UploadAction() {
		try {
			ConfigUtils.init();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 文件上传 专用action
	 * 
	 * @param input
	 * @param request
	 * @return
	 */
	@POST
	@Path("/upload")
	@Consumes("multipart/form-data")
	@Produces("application/json")
	public String uploadFile(MultipartFormDataInput input,
			@Context HttpServletRequest request) {
		logger.info(" upload action uploadFile() begin ...");
		String fileName = "";

		Result result = new Result();
		result.setSuccess(false);
		result.setResultCode(Result.ERROR_CODE);

		Map<String, List<InputPart>> uploadForm = input.getFormDataMap();
		List<InputPart> inputParts = uploadForm.get("f");

		if (null == inputParts || inputParts.size() == 0) {
			result.setMsg("你没有文件上传");
			logger.info(" uploadAction uploadFile() the input is null ");
		} else {

			InputPart inputPart = inputParts.get(0);
			String fid = request.getParameter("fid");

			FileConf fc = ConfigUtils.getFileConf(fid.substring(2));

			MultivaluedMap<String, String> header = inputPart.getHeaders();
			fileName = getFileName(header);
			String extName = FilenameUtils.getExtension(fileName).toLowerCase();
			String path = "";
			try {
				if (StringUtils.isNotBlank(fc.getAllowedTypes())
						&& fc.getAllowedTypes().indexOf(extName) == -1) {
					result.setMsg("文件格式不对");
					logger.info(" uploadAction uploadFile() the format is error ");
				} else if (0 != fc.getMaximumSize()
						&& fc.getMaximumSize() < inputPart.getBody(
								InputStream.class, null).available()) {
					result.setMsg("上传文件过大");
					logger.info(" uploadAction uploadFile() the size  is to larger ");
				} else {
					fileName = Encrypter.md5("" + System.currentTimeMillis()) + "." + extName;
					List<FileAttr> attrs = fc.getAttrs();
					for (FileAttr attr : attrs) {
						String desPath = request.getSession()
								.getServletContext().getRealPath(attr.getUrl())
								+ File.separator + fileName;
						path = desPath;
						if (attr.getWidth() == null && attr.getHeight() == null) {
							writeFile(inputPart, desPath);
						} else if (attr.getWidth() != null
								&& attr.getHeight() != null) {
							resizeImage(inputPart, desPath, attr.getWidth(),
									attr.getHeight(), extName);
						}
					}
					JSONObject jo = new JSONObject();
					jo.put("url", fc.getAttrs().get(0).getUrl() + "/"
							+ fileName);
					if (fid.startsWith("w")) {
						BufferedImage imgBuff = javax.imageio.ImageIO
								.read(new File(path));
						jo.put("fileName", fileName);
						jo.put("fileSize",
								inputPart.getBody(InputStream.class, null)
										.available());
						jo.put("imgWidth", imgBuff.getWidth());
						jo.put("imgHeight", imgBuff.getHeight());
					}
					result.setRows(jo);
					result.setSuccess(true);
					result.setResultCode(Result.SUCCESS_CODE);
				}
			} catch (IOException e) {
				result.setMsg("文件读写错误");
				logger.info(" uploadAction uploadFile() error  IOException...", e);
			}
		}
		logger.info(" uploadAction uploadFile() end ..." + result.toJson());
		return result.toJson();

	}

	private void resizeImage(InputPart in, String destFile, int width,
			int height, String format) throws IOException {
		InputStream inputStream = in.getBody(InputStream.class, null);
		File file = new File(destFile);
		if (!file.exists()) {
			file.getParentFile().mkdirs();
		}
		OutputStream fop = new FileOutputStream(file);
		resizeImage(inputStream, fop, width, height, format);
	}

	/**
	 * 改变图片的大小到宽为size，然后高随着宽等比例变化
	 * 
	 * @param is
	 *            上传的图片的输入流
	 * @param os
	 *            改变了图片的大小后，把图片的流输出到目标OutputStream
	 * @param size
	 *            新图片的宽
	 * @param format
	 *            新图片的格式
	 * @throws IOException
	 */
	private void resizeImage(InputStream is, OutputStream os, int newWidth,
			int newHeight, String format) throws IOException {
		BufferedImage prevImage = ImageIO.read(is);

		BufferedImage image = new BufferedImage(newWidth, newHeight,
				BufferedImage.TYPE_INT_BGR);
		Graphics graphics = image.createGraphics();
		graphics.drawImage(prevImage, 0, 0, newWidth, newHeight, null);
		ImageIO.write(image, format, os);
		os.flush();
		is.close();
		os.close();
	}

	/**
	 * header sample { Content-Type=[image/png], Content-Disposition=[form-data;
	 * name="file"; filename="filename.extension"] }
	 **/
	// 获得文件名称
	private String getFileName(MultivaluedMap<String, String> header) {

		String[] contentDisposition = header.getFirst("Content-Disposition")
				.split(";");

		for (String filename : contentDisposition) {
			if ((filename.trim().startsWith("filename"))) {

				String[] name = filename.split("=");

				String finalFileName = name[1].trim().replaceAll("\"", "");
				return finalFileName;
			}
		}
		return "unknown";
	}

	// 保存文件
	private void writeFile(InputPart inputPart, String filename)
			throws IOException {
		InputStream inputStream = inputPart.getBody(InputStream.class, null);
		File file = new File(filename);
		if (!file.exists()) {
			file.getParentFile().mkdirs();
		}
		FileOutputStream fop = new FileOutputStream(file);
		IOUtils.copy(inputStream, fop);
		fop.flush();
		fop.close();
	}
}
