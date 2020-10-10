package com.forms.learn.core.servlets;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ModifiableValueMap;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.Designate;

import com.day.cq.dam.api.AssetManager;
import com.day.cq.tagging.Tag;
import com.day.cq.tagging.TagManager;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import com.forms.learn.core.config.NameResourceConfig;

import javax.servlet.ServletException;
import java.io.IOException;

import javax.jcr.Node;
import javax.servlet.Servlet;

@Designate(ocd = NameResourceConfig.class, factory = true)
@Component(immediate = true, service = Servlet.class, property = { "sling.servlet.paths=" + "/bin/contentUpdate", })
public class UpdateTagServlet extends SlingSafeMethodsServlet {

	private String resourceName;
	private String nameSpaceName;
	private NameResourceConfig config;

	@Activate
	public void activate(NameResourceConfig config) {
		this.config = config;
		this.resourceName = config.getResourceName();
		this.nameSpaceName = config.getNameSpaceTag();

	}

	@Override
	protected void doGet(final SlingHttpServletRequest req, final SlingHttpServletResponse response) {
		try {

			ResourceResolver resourceResolver = req.getResourceResolver();
			Resource resource = resourceResolver.getResource(resourceName + "/jcr:content");

			TagManager tag_manager = resourceResolver.adaptTo(TagManager.class);
			String tag_object = "/content/cq:tags/" + nameSpaceName + "/" + req.getParameter("tagName");
			response.getWriter().print("Tag_Object" + tag_object);
			Tag resolveId = tag_manager.resolve(tag_object);

			int i = 0;

			Tag[] allTags = tag_manager.getTags(resource);
			Tag[] storeTags = new Tag[allTags.length + 1];
			if (allTags.length != 0)
				for (Tag t : allTags) {
					storeTags[i] = t;
					i++;
			}
			storeTags[storeTags.length - 1] = resolveId;

			tag_manager.setTags(resource, storeTags, true);

			ModifiableValueMap properties = resource.adaptTo(ModifiableValueMap.class);
			properties.put("jcr:description", "Setting the description.");
			properties.put("jcr:title", req.getParameter("tagName"));

			/*
			 * /conf/forms-data-poc/settings/wcm/templates/page-content
			 * /conf/my-app/settings/wcm/templates/content-page
			 */

			String templatePath = "/conf/forms-data-poc/settings/wcm/templates/page-content";
			boolean autoSave = true;

			PageManager pageManager = resourceResolver.adaptTo(PageManager.class);
			pageManager.create("/content/forms-data-poc/language-masters/en", "my-new-page", templatePath,
					"My New Page Title", autoSave);

			// if (!autoSave) { resourceResolver.commit(); }

			resource.getResourceResolver().commit();
			resourceResolver.close();
			response.getWriter().print("response from servlet :) ");
   
		} catch (Exception e) {

		} // make sure to close after all operations are done
		finally {

		}

	}

}
