(ns romannum.core)

(defn romannum [number] 
  (defn unparse [num one five ten] 
    (case num
      1 one
      2 (str one one)
      3 (str one one one)
      4 (str one five)
      5 (str five)
      6 (str five one)
      7 (str five one one)
      8 (str five one one one)
      9 (str one ten)
      ""))

  (str
    (unparse (mod (quot number 1000) 10) "M" "" "")
    (unparse (mod (quot number 100) 10) "C" "D" "M")
    (unparse (mod (quot number 10) 10) "X" "L" "C")
    (unparse (mod number 10) "I" "V" "X" )))

