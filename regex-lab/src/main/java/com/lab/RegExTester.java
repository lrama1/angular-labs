package com.lab;

import java.io.StringWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExTester {
	
	String routerString = 
			"(   \n" + 
			"		function(){	\n" + 
			"			angular.module('testapp', ['ngRoute']).config(function($routeProvider){	\n" + 
			"				$routeProvider	\n" + 
			"						.when('/view1', \n" + 
			"							{controller : 'PersonListController',		\n" + 
			"							templateUrl : '/test-web1/resources/js/angular_templates/view1.html'})\n" + 
			"						.when('/view2', {\n" + 
			"							controller : 'PersonListController',\n" + 
			"								templateUrl : '/test-web1/resources/js/angular_templates/view2.html' 	\n" + 
			"						})"
			+ "						.otherwise({	redirectTo : '/view1'	});	\n" + 
			"			})"
			+ "\n	;\n" + 
			"		}\n" + 
			"	)();";

			
	public String appendNewRouteExpression(String newWhenExpression){
		String whenRegex = "\\.when\\((.*?)\\)(\\;)*";
		Pattern whenPattern = Pattern.compile(whenRegex, Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
		//String value = routePattern.matcher(routerString).
		//System.out.println(value);
		Matcher matcher = whenPattern.matcher(routerString);	
		StringWriter whenWriter = new StringWriter();
		//1.  gather all 'when' expressions

		while(matcher.find()){
			System.out.println("===========>" + matcher.group());
			whenWriter.append("\n" + matcher.group());
		}
		routerString = matcher.replaceAll("INSERTHEREPLEASE");
		
		
		//2.  obtain the 'otherwise' expression (IF IT EXISTS)
		String otherwiseRegex = "\\.otherwise\\((.*?)\\)(\\;)*";
		Pattern otherwisePattern = Pattern.compile(otherwiseRegex, Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
		Matcher otherwiseMatcher = otherwisePattern.matcher(routerString);
		StringWriter otherwiseWriter = new StringWriter();
		while(otherwiseMatcher.find()){
			System.out.println("OTHERWISE EXPR===========>" + otherwiseMatcher.group());
			otherwiseWriter.append(otherwiseMatcher.group());
		}
		routerString = otherwiseMatcher.replaceAll("INSERTHEREPLEASE");
		
		//append the new route to the list of 'WHEN' EXPRESSIONS 
		whenWriter.append("\n" + newWhenExpression);
		
		System.out.println("=================================================================================");
		System.out.println(whenWriter.toString() + " " +  otherwiseWriter.toString());
		
		System.out.println("=================================================================================");
		int insertionPoint = routerString.indexOf("INSERTHEREPLEASE");
		StringBuffer stringBuffer = new StringBuffer(routerString);
		stringBuffer.insert(insertionPoint, whenWriter.toString() + " " +  otherwiseWriter.toString());
		
		return stringBuffer.toString().replaceAll("INSERTHEREPLEASE", "");
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RegExTester regExTester = new RegExTester();
		
		String whenExprToAdd = ".when('/view3', {controller : 'VehicleController', templateUrl : '/test-web1/resources/js/angular_templates/view3.html'})";
		
		System.out.println(regExTester.appendNewRouteExpression(whenExprToAdd));
	}

}
