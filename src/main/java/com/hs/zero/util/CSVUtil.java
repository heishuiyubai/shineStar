package com.hs.zero.util;

import org.apache.commons.codec.Charsets;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CSVUtil {

	/**
	 * 导出csv文件
	 *
	 * @param headers    内容标题
	 * @param exportData 要导出的数据集合
	 * @return
	 */
	public static byte[] exportCSV(List<String> headers, List<LinkedHashMap<String, Object>> exportData) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		BufferedWriter buffCvsWriter = null;
		try {
			// 编码gb2312，处理excel打开csv的时候会出现的标题中文乱码
			buffCvsWriter = new BufferedWriter(new OutputStreamWriter(baos, "gb2312"));
			// 写入cvs文件的头部
			Iterator<String> headerIterator = headers.iterator();
			while (headerIterator.hasNext()) {
				String header = headerIterator.next();
				buffCvsWriter.write("\"" + header + "\"");
				if (headerIterator.hasNext()) {
					buffCvsWriter.write(",");
				}
			}
			buffCvsWriter.newLine();
			// 写入文件内容
			Map.Entry<String, Object> propertyEntry = null;
			LinkedHashMap<String, Object> row = null;
			for (Iterator<LinkedHashMap<String, Object>> iterator = exportData.iterator(); iterator.hasNext(); ) {
				row = iterator.next();
				for (Iterator<Map.Entry<String, Object>> propertyIterator = row.entrySet().iterator(); propertyIterator.hasNext(); ) {
					propertyEntry = propertyIterator.next();
					buffCvsWriter.write("\"" + propertyEntry.getValue().toString() + "\"");
					if (propertyIterator.hasNext()) {
						buffCvsWriter.write(",");
					}
				}
				if (iterator.hasNext()) {
					buffCvsWriter.newLine();
				}
			}
			// 记得刷新缓冲区，不然数可能会不全的，当然close的话也会flush的，不加也没问题
			buffCvsWriter.flush();
		} catch (IOException e) {

		} finally {
			// 释放资源
			if (buffCvsWriter != null) {
				try {
					buffCvsWriter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return baos.toByteArray();
	}

	/**
	 * 设置下载文件需要的http头
	 */
	public static HttpServletResponse configResponse(HttpServletResponse response, String fileName) {
		if (StringUtils.isBlank(fileName)) {
			fileName = System.currentTimeMillis() + ".csv";
		} else if (!fileName.endsWith(".csv")) {
			fileName = fileName + ".csv";
		}
		try {
			fileName = URLEncoder.encode(fileName, Charsets.UTF_8.toString());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType(MediaType.APPLICATION_OCTET_STREAM.toString());
		return response;
	}
}

