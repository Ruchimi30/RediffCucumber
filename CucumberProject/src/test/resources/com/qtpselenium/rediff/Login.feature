#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template

#@Login
#Feature: Logging into Rediff Money


 # @Login
#Scenario Outline: Logging into Rediff
   # Given I open <Browser>
    #And I go to LoginURL
    #And I login inside the application
    #|Username|richierich.ma@gmail.com|
    #|Password|Rediff@30              |
   # Then login should be <Result>
  
    # Examples:
    #|Browser  | Result |
    #|Chrome   | failure|
    #|FireFox  | success|
   
   @Portfolio
  Feature: Logging into Rediff Money
   
   
   Background:
    Given I open Chrome
    And I go to LoginURL
    And I login inside the application
    |Username|richierich.ma@gmail.com|
    |Password|Rediff@30              |
    
 
 @CreatePortfolio
 Scenario Outline: Creating a Portfolio
 And I click on createPortfolio_id
 And I clear portfolioName_id
 And I type <data> in portfolioName_id field
 And I click on portfoliosubmit_id
 
 Examples:
 |data     |
 |Port12345|
 #|Port123  |
 
 
    
    

