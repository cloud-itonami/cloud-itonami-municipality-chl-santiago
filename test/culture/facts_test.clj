(ns culture.facts-test
  (:require [clojure.edn :as edn]
            [clojure.string :as str]
            [clojure.test :refer [deftest is]]
            [culture.facts :as facts]))

(deftest santiago-has-culture-basis
  (let [sb (facts/spec-basis "santiago")]
    (is (= 10 (count sb)))
    (is (= (count sb) (count (set (map :culture/id sb)))))
    (is (every? #(str/starts-with? (:culture/url %) "https://") sb))
    (is (every? #(= "santiago" (:culture/municipality %)) sb))
    (is (every? #(= "CHL" (:culture/country %)) sb))
    (is (every? #(seq (:culture/summary %)) sb))
    (is (every? #(string? (:culture/retrieved-at %)) sb))))

(deftest unknown-municipality-has-no-basis
  (is (nil? (facts/spec-basis "valparaiso")))
  (is (nil? (facts/spec-basis "zzz"))))

(deftest coverage-is-honest
  (let [c (facts/coverage ["santiago" "valparaiso"])]
    (is (= 2 (:requested c)))
    (is (= 1 (:covered c)))
    (is (= ["valparaiso"] (:missing-municipalities c)))))

(deftest by-kind-filters
  (is (= 4 (count (facts/by-kind "santiago" :dish))))
  (is (= ["santiago.beverage.terremoto" "santiago.beverage.mote-con-huesillo"]
         (mapv :culture/id (facts/by-kind "santiago" :beverage))))
  (is (empty? (facts/by-kind "santiago" :craft)))
  (is (empty? (facts/by-kind "valparaiso" :dish))))

(deftest tx-file-matches-catalog
  (let [tx (edn/read-string (slurp "data/culture-tx.edn"))
        flat (mapcat val (sort-by key facts/catalog))]
    (is (= (vec flat) (vec tx)))))
