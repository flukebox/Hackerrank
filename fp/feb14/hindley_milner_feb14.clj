(ns clojure-learn.hindley-milner-feb14)

;; HUGE GCD
(defn pf_n [n]
  (loop [d 2 n_ n pf {}]
    (cond (< n_ 2) pf
          (> d (Math/sqrt n)) (assoc pf n_ 1)
          :else
          (let [lst (loop [n__ n_ c 0]
                      (if (zero? (mod n__ d))
                        (recur (/ n__ d) (inc c))
                        (if (> c 0) [n__ c])))]
            (if (empty? lst) (recur (inc d) n_ pf) 
              (recur (inc d) (first lst) (assoc pf d (last lst))))))))

(defn all_pf [lst]
  (loop [l lst apfs {}]
    (if (empty? l) apfs
      (recur (butlast l) (apply merge-with + [apfs (pf_n (last l))])))))

(defn merge-common-with* [f a b]
  (merge-with f
              (select-keys a (keys b))
              (select-keys b (keys a))))
(def M 1000000007)
(defn modPow [b p_]
  (loop [t (long 1) p b pow p_ ]
    (if (zero? pow) t
      (recur (if (= (bit-and pow 1) 1) (mod (* t p) M) t) (mod (* p p) M) (bit-shift-right pow 1)))))

(defn gcd [lsta lstb]
  (let [pfa (all_pf lsta) pfb (all_pf lstb) com (apply merge-common-with* min [pfa  pfb])]
    (long (reduce #(mod (* %1 %2) M) (for [f com]
                                       (modPow (first f) (last f)))))))
(defn rlst [sc n]
  (loop [n_ n lst []]
    (if (zero? n_) lst
      (recur (dec n_) (cons (.nextInt sc) lst)))))

(let [sc (java.util.Scanner. *in*)      
      lsta (rlst sc (.nextInt sc))
      lstb (rlst sc (.nextInt sc)) ]
  (println (gcd lsta lstb)))

;; RMQ 


;; Recursive RMQ plain and simple just like 
(defn buildRMQ [data rmq start end idx]
  (cond 
    (= start end) (assoc rmq idx (nth data (- start 1)))
    :else
    (let [mid (bit-shift-right (+ start end) 1) l (bit-shift-left idx 1)  r (bit-or l 1)
          rmq_ (buildRMQ data (buildRMQ data rmq start mid l) (inc mid) end r)]
      (assoc rmq_ idx (min (get rmq_ l) (get rmq_ r))))))

(defn queryRMQ [rmq start end idx l r]
  (cond
    (or (< r start) (< end l)) (Integer/MAX_VALUE)
    (and (<= start l) (<= r end)) (get rmq idx)
    :else
    (let [mid (bit-shift-right (+ l r) 1) l_ (bit-shift-left idx 1) r_ (bit-or l_ 1)]
      (min (queryRMQ rmq start end l_ l mid) (queryRMQ rmq start end r_ (inc mid) r))))) 

(def queryRMQM (memoize queryRMQ))


(defn log2[n t]
  (if (zero? t)
    (int (Math/ceil (/ (Math/log n) (Math/log 2))))
    (int (Math/floor (/ (Math/log n) (Math/log 2))))))

(defn buildRMQStatic [data M n k ]
    (for [ j (range k) i (range n)]
      (if (zero? j) (aset M i j (nth data i))
        (let [ki (bit-shift-left 1 (dec j)) i_ (+ i ki)]
          (aset M i j (min (aget M i (dec j)) (if (< i_ n) (aget M i_ (dec j)) Integer/MAX_VALUE)))))))

(defn queryRMQStatic [M a b]
  (if (= a b)
    (aget M a 0)
    (let [ki (log2 (- b a) 1) bi (- b (bit-shift-left 1 ki))]
      (min (aget M a ki) (aget M (inc bi) ki)))))

(let [sc (java.util.Scanner. *in*)      
      n (.nextInt sc)
      m (.nextInt sc)
      le (.nextLine sc)
      k (inc (log2 n 0))
      data (map  #(Integer/parseInt %1) (clojure.string/split (.nextLine sc) #" "))
      M (to-array-2d (repeat n (repeat k Integer/MAX_VALUE)))]
  (dorun (buildRMQStatic data M n k))
  (doseq [i (range m)]
    (let [ l (.nextInt sc) r (.nextInt sc) ]
      (println (queryRMQStatic M l r)))))
       
 ;; BRAINF__K


