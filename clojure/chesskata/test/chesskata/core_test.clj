(ns chesskata.core-test
  (:require [clojure.test :refer :all]
            [chesskata.core :refer :all]))

(deftest chess-tests
  (testing "find diagnoals"
    (is (= (find-diags "d1" dec inc) ["d1" "e2" "f3" "g4" "h5"]))
    (is (= (find-diags "d1" inc dec) ["a4" "b3" "c2" "d1"])))

  (testing "bishops dont jo anything if they cannot see each other"
    (is (= (bishop-diagonal  "a1" "b1") ["a1" "b1"]))
    (is (= (bishop-diagonal  "a1" "a2") ["a1" "a2"])))

  (testing "bishops move away from each other if the can see each other for diagonals leaning right"
    (is (= (bishop-diagonal "a1" "b2") ["a1" "h8"]))
    (is (= (bishop-diagonal "b2" "d4") ["a1" "h8"]))
    (is (= (bishop-diagonal "d2" "f4") ["c1" "h6"])))

  (testing "bishops move away from each other if the can see each other for diagonals leaning left"
    (is (= (bishop-diagonal "b4" "d2") ["a5" "e1"]))
    (is (= (bishop-diagonal "a8" "b7") ["a8" "h1"]))
    (is (= (bishop-diagonal "b4" "d2") ["a5" "e1"]))))
