package com.lab;

import java.io.StringWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExTester2 {
	
	String htmlString = 
		"	<%@ page language=\"java\" contentType=\"text/html; charset=UTF-8\"   	\n" +
		"	pageEncoding=\"UTF-8\"%>   	\n" +
		"<%@ taglib prefix=\"c\" uri=\"http://java.sun.com/jsp/jstl/core\"%>   	\n" +
		"<%@taglib uri=\"http://www.springframework.org/tags\" prefix=\"spring\"%>   		\n" +
		"<!DOCTYPE html>   		\n" +
		"<html data-ng-app=\"xsample-web2\">   		\n" +
		"<head>   	\n" +
		"<title>xsample-web2</title>   	\n" +
		"	<script type=\"text/javascript\" src=\"https://ajax.googleapis.com/ajax/libs/angularjs/1.4.6/angular.min.js\"></script>   	\n" +
		"	<script type=\"text/javascript\" src=\"https://ajax.googleapis.com/ajax/libs/angularjs/1.4.6/angular-route.js\"></script>   	\n" +
		"	<script src=\"/xsample-web2/resources/js/angular_app.js\"></script>   	\n" +
		"	<script src=\"/xsample-web2/resources/js/angular_controllers/VehicleListController.js\"></script>   \n" +
		"	   \n" +
		"</head>   \n" +
		"<body>   \n" +
		"	<div class=\"container\" ng-view></div>   \n" +
		"</body>   \n" +
		"</html>";

			
	public String appendNewRouteExpression(String newScriptTag){
		String whenRegex = "\\<script(.*?)\\>(.*?)\\<\\/script\\>";
		Pattern whenPattern = Pattern.compile(whenRegex, Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
		//String value = routePattern.matcher(routerString).
		//System.out.println(value);
		Matcher matcher = whenPattern.matcher(htmlString);	
		StringWriter whenWriter = new StringWriter();
		//1.  gather all 'when' expressions
		while(matcher.find()){
			System.out.println("===========>" + matcher.group());
			whenWriter.append("\n" + matcher.group());
		}
		whenWriter.append("\n" + newScriptTag);
		htmlString = matcher.replaceAll("INSERTSCRIPTSHERE");
		
		
		System.out.println("=================================================================================");
		int insertionPoint = htmlString.indexOf("INSERTSCRIPTSHERE");
		StringBuffer stringBuffer = new StringBuffer(htmlString);
		stringBuffer.insert(insertionPoint, whenWriter.toString());
		return stringBuffer.toString().replaceAll("INSERTSCRIPTSHERE", "");
		

		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RegExTester2 regExTester = new RegExTester2();
		
		String whenExprToAdd = "<script><script>";
		
		System.out.println(regExTester.appendNewRouteExpression(whenExprToAdd));
	}

}
