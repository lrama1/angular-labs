package com.lab;

import java.io.StringWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExTester3 {
	
	String htmlString = " import { BrowserModule } from '@angular/platform-browser'; \n" +
	" import { NgModule } from '@angular/core'; \n" +
	" import { FormsModule} from '@angular/forms';\n" +
	" import { RouterModule, Routes } from '@angular/router'; \n" +
	" import { APP_BASE_HREF} from '@angular/common'; \n" +
	" import { HttpModule } from '@angular/http';  \n" +

	" import { ButtonModule, DataTableModule, PanelModule, SharedModule } from 'primeng/primeng'; \n" +
	" import { BrowserAnimationsModule} from '@angular/platform-browser/animations';  \n" +
	" import { AppComponent } from './app.component'; \n" +
	" import { VehicleListComponent } from './vehicle/vehicle-list/vehicle-list.component'; \n" +
	" import { VehicleEditComponent } from './vehicle/vehicle-edit/vehicle-edit.component'; \n" +
	" import {VehicleService} from './vehicle/vehicle.service'; \n" +
	" import { HomeComponent } from './home/home.component'; \n" +

	" const appRoutes: Routes = [ \n" +
	"   {    path: '**', component: HomeComponent}, \n" +
	"   { path: 'vehicles', component: VehicleListComponent}, \n" +
	"   { path: 'vehicle/:id', component: VehicleEditComponent} \n" +
	" ];" ;

	

			
	public String appendNewRouteExpression(String newScriptTag){
		String whenRegex = "\\{\\s*path\\s*:.*}";
		Pattern whenPattern = Pattern.compile(whenRegex, Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
		//String value = routePattern.matcher(routerString).
		//System.out.println(value);
		Matcher matcher = whenPattern.matcher(htmlString);	
		StringWriter whenWriter = new StringWriter();
		
		whenWriter.append(newScriptTag);
		//1.  gather all 'when' expressions
		while(matcher.find()){
			System.out.println("===========>" + matcher.group());
			whenWriter.append("\n" + matcher.group());
		}
		//whenWriter.append("\n" + newScriptTag);
		htmlString = matcher.replaceAll("INSERTSCRIPTSHERE");
		
		
		System.out.println("=================================================================================");
		int insertionPoint = htmlString.indexOf("INSERTSCRIPTSHERE");
		StringBuffer stringBuffer = new StringBuffer(htmlString);
		stringBuffer.insert(insertionPoint, whenWriter.toString());
		return stringBuffer.toString().replaceAll("INSERTSCRIPTSHERE", "");
		

		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RegExTester3 regExTester = new RegExTester3();
		
		String whenExprToAdd = "HAHA";
		
		System.out.println(regExTester.appendNewRouteExpression(whenExprToAdd));
	}

}
