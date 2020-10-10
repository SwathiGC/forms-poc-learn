package com.forms.learn.core.models;

import java.util.ArrayList;
import java.util.Iterator;

import javax.annotation.PostConstruct;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;

import com.day.cq.wcm.api.Page;

@Model(adaptables = Resource.class)
public class ChildTitlesModel {

	@SlingObject
	private Resource resource;

	@SlingObject
	private ResourceResolver resourceResolver;

	private ArrayList<String> listOfChild = new ArrayList();

	@PostConstruct
    protected void init() {
		listOfChild=getChildPagesTitle();
	}
		private ArrayList<String> getChildPagesTitle(){
			Page page = resource.adaptTo(Page.class);
			if(null!= page) 
			{
				Iterator<Page> childPageList=page.listChildren();
				while(childPageList.hasNext())
				{
					Page currentChild = (Page) childPageList.next();
					String currentChildTitle=currentChild.getTitle();
					listOfChild.add(currentChildTitle);
				}
				
			}
			return listOfChild;
		}
				
				
		
		
	public ArrayList<String> getListOfChild() {
		return listOfChild;
	}
  }
	
	
	
	
	


