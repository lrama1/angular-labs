package com.lab;

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

			
	public void test(){
		String whenRegex = "\\.when\\((.*?)\\)(\\;)*";
		Pattern whenPattern = Pattern.compile(whenRegex, Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
		//String value = routePattern.matcher(routerString).
		//System.out.println(value);
		Matcher matcher = whenPattern.matcher(routerString);		
		//1.  gather all 'when' expressions
		while(matcher.find()){
			System.out.println("===========>" + matcher.group());
		}
		
		//2.  obtain the 'otherwise' expression (IF IT EXISTS)
		String otherwiseRegex = "\\.otherwise\\((.*?)\\)(\\;)*";
		Pattern otherwisePattern = Pattern.compile(otherwiseRegex, Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
		Matcher otherwiseMatcher = otherwisePattern.matcher(routerString);
		while(otherwiseMatcher.find()){
			System.out.println("OTHERWISE===========>" + otherwiseMatcher.group());
		}
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RegExTester regExTester = new RegExTester();
		regExTester.test();
	}

}
