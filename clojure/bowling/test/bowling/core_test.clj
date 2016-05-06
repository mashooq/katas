(ns bowling.core-test
  (:require [clojure.test :refer :all]
            [bowling.core :refer :all]))

(deftest bowling 
  (testing "strikes for all rolls"
    (is (= 300 (score "XXXXXXXXXXXX"))))

  (testing "normal scores"
    (is (= 99 (score "91919393929291219191"))))

  (testing "normal scores or misses"
    (is (= 90 (score "9-9-9-9-9-9-9-9-9-9-")))
    (is (= 93 (score "919-9-9-9-9-929-9-9-"))))
  
  (testing "mixture of stikes and normals"
    (is (= 98 (score "9-X8-9-9-9-9-9-9-9-")))
    (is (= 104 (score "9-X8-9-9-9-9-9-9-X23")))
    (is (= 28 (score "--X81--------------")))
    (is (= 27 (score "--X8-1-------------"))))
  
  (testing "spares for all rolls"
    (is (= 150 (score "5/5/5/5/5/5/5/5/5/5/5"))))

  (testing "mixture of spares and normals"
    (is (= 82 (score "9-8/--9-9-9-9-9-9-9-")))
    (is (= 84 (score "9-8/--9-9-9-9-9-9-9/1")))
    (is (= 12 (score "--8/1---------------")))
    (is (= 11 (score "--8/-1--------------")))))

