package com.bmp.utils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.fileupload.FileItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.commons.util.FileUtils;
import com.model.FileContent;
import com.tables.FormFields;

public class ServletUtils {

	private static final Logger LOG = LoggerFactory.getLogger(ServletUtils.class);

	private ServletUtils() {

	}

	public static List<FormFields> getFormFieldContents(List<FileItem> fileItems) {
		if (CollectionUtils.isEmpty(fileItems)) {
			// TODO throw new FileItemsNotFoundException
		}

		List<FormFields> formFields = new ArrayList<FormFields>();
		for (FileItem fileItem : fileItems) {
			if (fileItem.isFormField()) {
				FormFields formField = new FormFields();
				formField.setName(fileItem.getFieldName());
				formField.setValue(fileItem.getString());
				formFields.add(formField);
			}
		}
		LOG.debug("Form fields {}", formFields);
		return formFields;
	}

	public static List<FileContent> getMultipartContents(List<FileItem> fileItems) throws IOException {
		if (CollectionUtils.isEmpty(fileItems)) {
			// TODO throw new FileItemsNotFoundException
		}

		List<FileContent> fileContentList = new ArrayList<FileContent>();
		for (FileItem fileItem : fileItems) {
			if (!fileItem.isFormField()) {
				FileContent fileContent = new FileContent();
				String fileName = fileItem.getName();
				fileContent.setFileName(fileName);
				LOG.debug("Filename {}", fileName);
				fileContent.setBytes(FileUtils.toByteArray(fileItem.getInputStream()));
				InputStream inputStream = new ByteArrayInputStream(fileContent.getBytes());
				fileContent.setInputStream(inputStream);
				fileContentList.add(fileContent);
			}
		}
		return fileContentList;
	}

	public static String getParameter(List<FormFields> formFieldList, String name) {
		for (FormFields formFields : formFieldList) {
			if (name.equals(formFields.getName())) {
				return formFields.getValue();
			}
		}
		return null;
	}
}
