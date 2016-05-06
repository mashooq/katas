(ns romannum.core-test
  (:use midje.sweet)
  (:use [romannum.core]))

(fact "converts a number to roman numeral" 
  (romannum 1) => "I"
  (romannum 2) => "II"
  (romannum 3) => "III"
  (romannum 4) => "IV"
  (romannum 5) => "V"
  (romannum 6) => "VI"
  (romannum 7) => "VII"
  (romannum 8) => "VIII"
  (romannum 9) => "IX"
  (romannum 10) => "X"
  (romannum 40) => "XL"
  (romannum 50) => "L"
  (romannum 90) => "XC"
  (romannum 501) => "DI"
  (romannum 900) => "CM"
  (romannum 1800) => "MDCCC"
  (romannum 1870) => "MDCCCLXX"
  (romannum 3500) => "MMMD")
