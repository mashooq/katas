(ns marsrover.core-test
  (:require [clojure.test :refer :all]
            [clojure.spec.test.alpha :as st]
            [marsrover.core :refer :all]))

(st/instrument `move)

(def grid {:w 10 :h 8})
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
    (let [grid {:w 10 :h 10 :obstacles [{:x 4 :y 4}]}]
      (is (= (move grid {:x 2 :y 2 :d :N} "MMRMM") {:x 3 :y 4 :d :E}))))

  (testing "does not allow an invalid rover state"
    (is (thrown? Exception (move-in-grid {:x 8 :y 0 :d :X} "M")))
    (is (thrown? Exception (move-in-grid {:x 9 :y -1 :d :N} "M")))
    (is (thrown? Exception (move-in-grid {:x -1 :y 0 :d :N} "M"))))

  (testing "does not allow an invalid grid"
    (is (thrown? Exception (move {:w -1 :h 10} {:x 9 :y 0 :d :N} "M")))
    (is (thrown? Exception  (move {:w 0 :h 1} {:x 9 :y 0 :d :N} "M")))
    (is (thrown? Exception (move {:w 1 :h 1 :obstacles [:x :y]} {:x 9 :y 0 :d :N} "M")))
    (is (thrown? Exception (move {:w 1 :h 1 :obstacles [:x 10 :y 10]} {:x 9 :y 0 :d :N} "M")))
    (is (thrown? Exception (move {:w 1 :h 1 :obstacles [{:x -1 :y 10}]} {:x 9 :y 0 :d :N} "M")))
    (is (thrown? Exception (move {:w 1 :h 1 :obstacles [{:x 10 :y -1}]} {:x 9 :y 0 :d :N} "M")))))
