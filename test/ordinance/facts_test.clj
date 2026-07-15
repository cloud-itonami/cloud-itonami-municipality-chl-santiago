(ns ordinance.facts-test
  (:require [clojure.string :as str]
            [clojure.test :refer [deftest is]]
            [ordinance.facts :as facts]))

(deftest santiago-has-spec-basis
  (let [sb (facts/spec-basis "santiago")]
    (is (= 2 (count sb)))
    (is (every? #(str/starts-with? (:ordinance/url %) "https://documentos.munistgo.cl/") sb))))

(deftest unknown-municipality-has-no-spec-basis
  (is (nil? (facts/spec-basis "lima")))
  (is (nil? (facts/spec-basis "zzz"))))

(deftest coverage-is-honest
  (let [c (facts/coverage ["santiago" "lima"])]
    (is (= 2 (:requested c)))
    (is (= 1 (:covered c)))
    (is (= ["lima"] (:missing-municipalities c)))))

(deftest by-topic-filters
  (is (= ["santiago.reglamento-971-transparencia-activa"]
         (mapv :ordinance/id (facts/by-topic "santiago" :transparency))))
  (is (empty? (facts/by-topic "santiago" :labor)))
  (is (empty? (facts/by-topic "lima" :public-order))))
