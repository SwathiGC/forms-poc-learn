package com.forms.learn.core.config;


import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;
@ObjectClassDefinition(name = "Config for Updating Resources", description = "For resource")
public @interface NameResourceConfig {

	@AttributeDefinition(name="Provide the Resource" ,description = "resource path")
	String getResourceName() default "/content/dam/adobe-logo.jpg";
	
	@AttributeDefinition(name="Provide the NameSpace of the Tag", description = "nameSpace for tagging")
	String getNameSpaceTag() default "custom-tag";
}


   
	
