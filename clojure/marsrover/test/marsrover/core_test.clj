(ns marsrover.core-test
  (:require [clojure.test :refer :all]
            [marsrover.core :refer :all]))

(deftest mars-rover
  (testing "moves forward"
    (is (= (move {:x 0 :y 0 :d :N} "MMM") {:x 0 :y 3 :d :N}))
    (is (= (move {:x 0 :y 0 :d :E} "MMM") {:x 3 :y 0 :d :E}))
    (is (= (move {:x 0 :y 0 :d :S} "MMM") {:x 0 :y -3 :d :S}))
    (is (= (move {:x 0 :y 0 :d :W} "MMM") {:x -3 :y 0 :d :W})))

  (testing "turns right"
    (is (= (move {:x 0 :y 0 :d :N} "R") {:x 0 :y 0 :d :E}))
    (is (= (move {:x 0 :y 0 :d :E} "R") {:x 0 :y 0 :d :S}))
    (is (= (move {:x 0 :y 0 :d :S} "R") {:x 0 :y 0 :d :W}))
    (is (= (move {:x 0 :y 0 :d :W} "R") {:x 0 :y 0 :d :N}))))
