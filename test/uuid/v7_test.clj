(ns uuid.v7-test
  (:require
   [uuid.v7 :as sut]
   [clojure.test :refer :all])
  (:import (java.time Instant)
           (java.util UUID)))

(deftest uuidv7
  (testing "generate random"
    (is (instance? UUID (sut/random-uuidv7))))

  (testing "construct random from instant"
    (is (instance? UUID (sut/random-uuidv7 (Instant/now)))))

  (testing "construct random from epochMillis"
    (is (instance? UUID (sut/random-uuidv7 (.toEpochMilli (Instant/now))))))

  (testing "construct random from date"
    (is (instance? UUID (sut/random-uuidv7 (java.util.Date.)))))

  (testing "extract to instant"
    (let [i (java.time.Instant/now)]
      (is (= (.toEpochMilli i)
             (.toEpochMilli (sut/uuidv7->instant (sut/random-uuidv7 i)))))))

  (testing "extract to date"
    (let [d (java.util.Date.)]
      (is (= (.toEpochMilli (.toInstant d))
             (.toEpochMilli (.toInstant (sut/uuidv7->date (sut/random-uuidv7 d)))))))))
