(ns core)

(defn div-by-100? [year]
  (zero? (mod year 100)))

(defn div-by-400? [year]
  (zero? (mod year 400)))

(defn div-by-4? [year]
  (zero? (mod year 4)))

(defn leap-year? [year]
  (if (< year 100)
    (div-by-4? year)
    (if (div-by-100? year)
      (div-by-400? year)
      (div-by-4? year))))
