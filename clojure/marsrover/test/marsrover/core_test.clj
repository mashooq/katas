(ns marsrover.core-test
  (:require [clojure.test :refer :all]
            [marsrover.core :refer :all]))

(deftest marsrover
  (start-position {:x 0 :y 0 :d :N})
  (navigation-grid {:x 10 :y 10 :obstacles '({:x 5 :y 4})})

  (testing "no moves notifies of current position"
    (is (= (move '()) {:x 0 :y 0 :d :N})))

  (testing "moves forward steps with from initial position"
    (is (= (move '(\f))     {:x 0 :y 1 :d :N}))
    (is (= (move '(\f \f))  {:x 0 :y 3 :d :N})))

  (testing "moves back steps with from initial poistion"
    (is (= (move '(\b)) {:x 0 :y 2 :d :N}))
    (is (= (move '(\b \b)) {:x 0 :y 0 :d :N})))

  (testing "wrap around if moves past grid"
    (is (= (move '(\f \f \f \f \f \f \f \f \f \f)) {:x 0 :y 0 :d :N}))
    (is (= (move '(\b)) {:x 0 :y 9 :d :N}))
    (is (= (move '(\f)) {:x 0 :y 0 :d :N})))

  (testing "move after turning right and wrap around grid"
    (is (= (move '(\r \f)) {:x 1 :y 0 :d :E}))
    (is (= (move '(\r \f)) {:x 1 :y 9 :d :S}))
    (is (= (move '(\r \f)) {:x 0 :y 9 :d :W}))
    (is (= (move '(\r \f)) {:x 0 :y 0 :d :N}))

    (is (= (move '(\r \b)) {:x 9 :y 0 :d :E}))
    (is (= (move '(\r \b)) {:x 9 :y 1 :d :S}))
    (is (= (move '(\r \b)) {:x 0 :y 1 :d :W}))
    (is (= (move '(\r \b)) {:x 0 :y 0 :d :N})))

  (testing "move after turning left"
    (is (= (move '(\l \f)) {:x 9 :y 0 :d :W}))
    (is (= (move '(\l \f)) {:x 9 :y 9 :d :S}))
    (is (= (move '(\l \f)) {:x 0 :y 9 :d :E}))
    (is (= (move '(\l \f)) {:x 0 :y 0 :d :N})))

  (testing "stopped by obstacles"
    (is (= (move '(\f \f \f \f \r \f \f \f \f)) {:x 4 :y 4 :d :E}))
    (is (= (move '(\f)) {:x 4 :y 4 :d :E}))
    (is (= (move '(\l \f)) {:x 4 :y 5 :d :N}))))
