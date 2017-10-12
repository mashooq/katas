(ns marsrover.core-test
  (:require [clojure.test :refer :all]
            [marsrover.core :refer :all]))

(def grid {:x 10 :y 8})
(def move-in-grid (partial move grid))

(deftest mars-rover
  (testing "moves forward"
    (is (= (move-in-grid {:x 0 :y 0 :d :N} "MMM") {:x 0 :y 3 :d :N}))
    (is (= (move-in-grid {:x 0 :y 0 :d :E} "MMM") {:x 3 :y 0 :d :E}))
    (is (= (move-in-grid {:x 0 :y 5 :d :S} "MMM") {:x 0 :y 2 :d :S}))
    (is (= (move-in-grid {:x 5 :y 0 :d :W} "MMM") {:x 2 :y 0 :d :W})))

  (testing "turns right"
    (is (= (move-in-grid {:x 0 :y 0 :d :N} "R") {:x 0 :y 0 :d :E}))
    (is (= (move-in-grid {:x 0 :y 0 :d :E} "R") {:x 0 :y 0 :d :S}))
    (is (= (move-in-grid {:x 0 :y 0 :d :S} "R") {:x 0 :y 0 :d :W}))
    (is (= (move-in-grid {:x 0 :y 0 :d :W} "R") {:x 0 :y 0 :d :N})))

  (testing "turns left"
    (is (= (move-in-grid {:x 0 :y 0 :d :N} "L") {:x 0 :y 0 :d :W}))
    (is (= (move-in-grid {:x 0 :y 0 :d :W} "L") {:x 0 :y 0 :d :S}))
    (is (= (move-in-grid {:x 0 :y 0 :d :S} "L") {:x 0 :y 0 :d :E}))
    (is (= (move-in-grid {:x 0 :y 0 :d :E} "L") {:x 0 :y 0 :d :N})))

  (testing "wraps around a grid"
    (is (= (move-in-grid {:x 0 :y 7 :d :N} "M") {:x 0 :y 0 :d :N}))
    (is (= (move-in-grid {:x 0 :y 0 :d :S} "M") {:x 0 :y 7 :d :S}))
    (is (= (move-in-grid {:x 9 :y 0 :d :E} "M") {:x 0 :y 0 :d :E}))
    (is (= (move-in-grid {:x 0 :y 0 :d :W} "M") {:x 9 :y 0 :d :W})))

  (testing "stopped by obstacles"
    (let [grid {:x 10 :y 10 :obstacles [{:x 4 :y 4}]}]
      (is (= (move grid {:x 2 :y 2 :d :N} "MMRMM") {:x 3 :y 4 :d :E})))))
