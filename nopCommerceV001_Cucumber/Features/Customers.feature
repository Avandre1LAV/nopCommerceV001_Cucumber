Feature: Customers

Background: Below are the common steps for each scenarios
	Given User Launch Chrome Browser
	When User open URL "http://admin-demo.nopcommerce.com/login"
	And User Enter Email as "admin@yourstore.com" and Password as "admin"
	And Click on Login
	Then User can view Dashborad

@sanity
Scenario: Add a New Customer
	When User click on customers Menu
	And Click on Customer Menu Item
	And Click on Add New Button
	Then user can view Add new Customer page
	When User enter customer info
	And Click Save button
	Then User can view confirmation message "The new customer has been added successfully."
	And Close Browser

@regression	
Scenario: Search the customer by usinng EmailID
	When User click on customers Menu
	And Click on Customer Menu Item
	And Enter customer Email
	When Click on search button
	Then User should found Email in the Search table
	And Close Browser

@regression	
Scenario: Search the customer by name
	When User click on customers Menu
	And Click on Customer Menu Item
	And Enter customer First Name
	And Enter customer Last Name
	When Click on search button
	Then User should found Name in the Search table
	And Close Browser
	
