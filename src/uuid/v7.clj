(ns uuid.v7
  (:import (com.fasterxml.uuid Generators)
           (com.fasterxml.uuid.impl UUIDUtil)
           (java.time Instant)
           (java.util Date)))

(defonce random-uuidv7-generator
  (Generators/timeBasedEpochRandomGenerator))

(defn random-uuidv7
  ([x]
   (let [x (cond-> x
             (instance? Instant x) (.toEpochMilli)
             (instance? Date x) (#(.toEpochMilli (.toInstant %))))]
     (.construct random-uuidv7-generator x)))
  ([]
   (.generate random-uuidv7-generator)))

(defn uuidv7->instant [uuidv7]
  (let [epoch-milli (UUIDUtil/extractTimestamp uuidv7)]
    (Instant/ofEpochMilli epoch-milli)))

(defn uuidv7->date [uuidv7]
  (let [epoch-milli (UUIDUtil/extractTimestamp uuidv7)]
    (Date. epoch-milli)))
