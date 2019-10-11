(ns demo-test
  (:require [clojure.test :refer :all]
            [demo :refer :all]))


(defn leap-year? [year]
  (let [div-by? (partial
                  (fn [num] (zero? (mod year num))))]

    (or (div-by? 400)
        (and (div-by? 4)
             (not (div-by? 100))))))

(defn div-by-100? [year]
  (zero? (mod year 100)))

(defn div-by-400? [year]
  (zero? (mod year 400)))

(defn div-by-4? [year]
  (zero? (mod year 4)))

(defn leap-year1? [year]
  (if (< year 100)
    (div-by-4? year)
    (if (div-by-100? year)
      (div-by-400? year)
      (div-by-4? year))))

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
