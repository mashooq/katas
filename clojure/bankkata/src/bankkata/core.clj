(ns bankkata.core
  (:require [java-time :as jt]))

(defprotocol Account
    (reset [acc])
    (add [acc txn])
    (txns [acc]))

(extend-type clojure.lang.PersistentVector
  Account
  (reset [acc] [])
  (add [acc txn] (conj acc txn))
  (txns [acc] acc))

(def account (atom []))

(defn new-account [] (swap! account reset))
(defn local-date [] (jt/local-date))

(defn- add-txn [type amount]
  (let [date (local-date)]
    (swap! account add {:type type :date date :amount amount}))
  nil)

(defn deposit [amount] (add-txn :credit amount))
(defn withdrawl [amount] (add-txn :debit amount))



(defn print-to-console [] #(println %))

(defn- format-amount [txn]
  (case (:type txn)
    :credit (format "%.2f" (:amount txn))
    :debit (str "-" (format "%.2f" (:amount txn)))))

(defn- format-transaction [txn balance]
  (str
   (jt/format "dd/MM/yyyy" (:date txn)) " | "
   (format-amount txn) " | "
   (format "%.2f" balance)))

(defn- new-balance [balance txn]
  (case (:type txn)
    :credit (+ balance (:amount txn))
    :debit (- balance (:amount txn))))

(defn- format-transactions [txns balance]
  (if (seq txns)

    (let [txn (first txns)
         new-balance (new-balance balance txn)]
      (conj (format-transactions (rest txns) new-balance)
            (format-transaction txn new-balance)))
    []))

(defn print-statement []
  ((print-to-console)
   (cons  "DATE       | AMOUNT  | BALANCE"
          (format-transactions (txns @account) 0))))
