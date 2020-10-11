package com.forms.learn.core.models;

import java.util.ArrayList;
import java.util.List;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;

import javax.annotation.PostConstruct;

@Model(adaptables = Resource.class)
public class ChildTitlesModel {

	@SlingObject
	private Resource resource;

	@SlingObject
	private ResourceResolver resourceResolver;

	public List<String> getListOfChild() {
		return listOfChild;
	}

	private List<String> listOfChild = new ArrayList<>();

	public String getVal() {
		return val;
	}

	private String val = "deaf";

	@PostConstruct
    public void activate() {
			final Iterable<Resource> childPageList = resource.getChildren();
			listOfChild.add("asd");
			for (Resource res : childPageList) {
				final String currentChildTitle = res.getPath();
				listOfChild.add(currentChildTitle);
			}
	}
  }
	
	
	
	
	


