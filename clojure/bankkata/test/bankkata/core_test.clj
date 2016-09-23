(ns bankkata.core-test
  (:require [midje.sweet :refer :all]
            [bankkata.core :refer :all]
            [clojure.string :as str]
            [java-time :as jt]))

(against-background
 [(before :facts (new-account))
  (print-to-console) => (fn [stmt] stmt)]

 (fact "prints statement header"
       (first (print-statement)) =>  "DATE       | AMOUNT  | BALANCE")

 (fact "statement reflects deposits into the account"
       (deposit 1000.0) => nil
       (provided (local-date) => (jt/local-date 2012 01 10))

       (deposit 1000.0) => nil
       (provided (local-date) => (jt/local-date 2012 01 10))

       (print-statement) => (just  "DATE       | AMOUNT  | BALANCE"
                                   "10/01/2012 | 1000.00 | 2000.00"
                                   "10/01/2012 | 1000.00 | 1000.00"))

 (fact "statement reflects withdrawls and deposits from the account"
       (deposit 1000.0) => nil
       (provided (local-date) => (jt/local-date 2014 04 01))

       (withdrawl 100.0) => nil
       (provided (local-date) => (jt/local-date 2014 04 02))

       (deposit 500.0) => nil
       (provided (local-date) => (jt/local-date 2014 04 10))

       (print-statement) => (just  "DATE       | AMOUNT  | BALANCE"
                                  "10/04/2014 | 500.00 | 1400.00"
                                  "02/04/2014 | -100.00 | 900.00"
                                  "01/04/2014 | 1000.00 | 1000.00")))
