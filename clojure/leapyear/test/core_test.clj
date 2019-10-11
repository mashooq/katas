(ns core-test
  (:require [clojure.test :refer :all]
            [core :refer :all]))

(deftest a-year
  (testing "is not a leap year if not divisible by 4"
    (is (= (leap-year? 1997) false))
    (is (= (leap-year? 2) false))
    (is (= (leap-year? 5) false)))

  (testing "is a leap year if divisible by 4"
    (is (= (leap-year? 1996) true))
    (is (= (leap-year? 4) true))
    (is (= (leap-year? 8) true)))

  (testing "is a leap year if divisible by 400"
    (is (= (leap-year? 400) true))
    (is (= (leap-year? 1600) true))
    (is (= (leap-year? 8) true)))

  (testing "is not leap year if divisible by 100 but not 400"
    (is (= (leap-year? 1800) false))))
