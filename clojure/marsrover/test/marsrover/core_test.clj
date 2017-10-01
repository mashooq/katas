(ns marsrover.core-test
  (:require [clojure.test :refer :all]
            [marsrover.core :refer :all]))

(deftest mars-rover
  (testing "moves forward"
    (is (= (move {:x 0 :y 0 :d :N} "MMM") {:x 0 :y 3 :d :N}))))
