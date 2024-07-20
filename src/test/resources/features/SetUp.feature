@InitialSetUp
Feature: Feature to test initial set up

  Background:
    Given User unlock the cellphone

  @001-scenario
  Scenario: User search anything in the browser
     Given User tap Chrome button
#    When User type "cypress" in the searcher
#    Then User see a list of results