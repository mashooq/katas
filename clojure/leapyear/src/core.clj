(ns core)

(defn leap-year? [year]
  (let [div-by? (partial #(zero? (mod year %)))]

    (or (div-by? 400)
        (and (div-by? 4)
             (not (div-by? 100))))))
