(ns bankkata.core
  (:require [java-time :as jt])
  (:refer-clojure :exclude [print]))

(defn print-to-console [] #(println %))
(defn local-date [] (jt/local-date))

(defprotocol TransactionStore
  (add [ts txn])
  (txns [ts]))
(extend-type clojure.lang.PersistentVector
  TransactionStore 
  (add [ts txn] (conj ts txn))
  (txns [ts] ts))


(defprotocol StatementPrinter
  (print-txns [sp ts]))
(defn statement-printer [printfn]
  (letfn [(format-amount [txn]
            (case (:type txn)
              :credit (format "%.2f" (:amount txn))
              :debit (str "-" (format "%.2f" (:amount txn)))))

          (format-transaction [txn balance]
            (str
             (jt/format "dd/MM/yyyy" (:date txn)) " | "
             (format-amount txn) " | "
             (format "%.2f" balance)))

          (new-balance [balance txn]
            (case (:type txn)
              :credit (+ balance (:amount txn))
              :debit (- balance (:amount txn))))

          (format-transactions [txns balance]
            (if (seq txns)
              (let [txn (first txns)
                    new-balance (new-balance balance txn)]
                (conj (format-transactions (rest txns) new-balance)
                      (format-transaction txn new-balance)))
              []))

          (print-txns-with-header [txns]
            (printfn
             (cons  "DATE       | AMOUNT  | BALANCE"
                    (format-transactions txns 0))))]

    (reify StatementPrinter
      (print-txns [sp ts] (print-txns-with-header (txns ts))))))


(def account (atom []))
(defn new-account [] (reset! account []))
(defn deposit [amount] (swap! account add {:type :credit :date (local-date) :amount amount}) nil)
(defn withdrawl [amount] (swap! account add {:type :debit :date (local-date) :amount amount}) nil)
(defn print-statement [] (-> (statement-printer (print-to-console)) (print-txns @account)))
