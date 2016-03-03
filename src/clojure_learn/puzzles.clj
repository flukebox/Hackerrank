(ns clojure-learn.puzzles)

; Generate inifite series and take first 10
(take 10 (repeatedly #(+ 1 (rand-int 5))))

; Define a function randX to generate number withing [1,x] both inclusive
(defn randX [x] (+ 1 (rand-int x)))

; generate random 5
(defn rand5 [] (randX 5))

; generate random 7 from random 5
(defn rand7 []
  (let [ x (rand5)
        y (rand5)
        z (+ (* 5 x) y)]
    (if (< 22  z)
      (mod z 7)
      (rand7) )))

; Node def struct
(defstruct node :next :prev :val)

; need dynamic metadata so v can be changed in binding
(def ^:dynamic v 1)

(defn change-it []
  (println "2) v=" v ); -> 1
  (def v 2) ; changing mapping to 2
  (println "3) v=" v)    ; -> 2
  (binding [v 3]
    (println "4) v=" v)  ; -> 3
    (set! v 4)
    (println "5) v=" v)) ; -> 4
  (println "6) v=" v))    ; -> 2

(println "1) v=" v)      ; -> 1

(let [thread (Thread. #(change-it))]
  (.start thread)
  (.join thread))

(println "7) v=" v)


(let [sc (java.util.Scanner. *in*)]
  (doseq [i (range 0 (.nextInt sc))]
    (println "Hello World")))

(def prime 100000007)
(defn fib [a b] (cons a (lazy-seq (fib b (mod (+ b a) prime)))))
(def fib-seq-n (take 10001 (fib  0 1 )))

(let [sc (java.util.Scanner. *in*)
      tc (.nextInt sc)]
  (doseq [i (range 0 tc)]
    (println (nth fib-seq-n  (.nextInt sc)))))

(def lst [ 1 2 3 4 5])
(reduce conj '() lst)


(defn rev [lst]
  (loop [ l []  bl lst]
    (if (empty? bl) l
      (recur (conj l (last bl)) (butlast bl)))))


(defn popodd [lst]
  (loop [l [] r lst n 0]
    (if (empty? r) l
      (if (even? n)
        (recur (conj l (first r)) (rest r) (inc n))
        (recur l (rest r) (inc n))))))

(defn len [lst]
  (loop [ n 0 l lst]
    (if (empty? l) n
      (recur (inc n) (pop l)))))

((fn [lst]
   (loop [ n 0 l lst]
     (if (empty? l)n
       (recur (inc n) (pop l))))) [1 2 3] 3)

((fn [n lst]
   (for [e lst i (range 0 n)] e)) [1 2 3 4] 2)


(def cc [1 2 5 10])

(defn count-change [n k]
  (cond
    (or (< n 0) (< k 0)) 0 
    (= n 0) 1
    :else (+    
            (count-change n (- k 1)) 
            (count-change (- n (nth cc k)) k))))

(defn count-change-with-coins [n k gcc]
  (cond
    (or (< n 0) (< k 0)) 0 
    (= n 0) 1
    :else (+    
            (count-change-with-coins n (- k 1) gcc) 
            (let [ ccount (nth gcc k) cv (nth cc k) ]
              (if (> ccount 0) 
                (count-change-with-coins (- n cv) k (assoc gcc k (dec ccount))) 
                0)))))

(defn power
  ([x y] (power x y 1))
  ([x y current]
    (if (= y 0)
      current
      (if (> y 0)
        (recur x (- y 1) (* x current))
        (recur x (+ y 1) (/ current x))))))     


(def k 3)
(let [sc (java.util.Scanner. *in*)
      testcases (.nextInt sc)]
  (doseq [i (range 0 testcases)]
    (let [n (.nextInt sc) gcc [ (.nextInt sc) (.nextInt sc) (.nextInt sc) (.nextInt sc)]]
      (println (count-change-with-coins n k gcc)))))



(defn gcd [a b]
  (if (zero? b) a
    (recur b (mod a b))))


(let [sc (java.util.Scanner. *in*)
      a (.nextInt sc) b (.nextInt sc)]
  (println ((fn [a_ b_]
              (if (zero? b_) 
                a_
                (recur b_ (mod a_ b_)))) a b)))


(let [sc (java.util.Scanner. *in*)
      testcases (.nextInt sc)]
  (doseq [i (range 0 testcases)]
    (let [n (.nextDouble sc)]
      (println ((fn [x_]
                  (format "%.4f" 
                          (reduce + 
                                  (take 10 
                                        (cons 1.0 
                                              ((fn exp [x v n]
                                                 (cons 
                                                   (/ (* x v ) n) 
                                                   (lazy-seq (exp x (/ (* x v ) n) (inc n))))) x_ 1.0 1.0)))))) n)))))


(let [sc (java.util.Scanner. *in*)
      testcases (.nextInt sc)
      bst-count ((memoize (fn [n]
                            (loop [n_ 1 c [1 1]]
                              (if (= n_ n)
                                c
                                (recur 
                                  (inc n_) 
                                  (conj c 
                                        (reduce 
                                          #(mod (+ %1 %2) 100000007)  (for [i (range 0 (inc n_))]
                                                                        (mod (* (nth c i) (nth c (- n_ i))) 100000007))))))))) 1000)
      ]
  (doseq [i (range 0 testcases)]
    (println (nt2h bst-count (.nextInt sc)))))


(defn nCr [n r]
  (cond (= r 0) 1
        (= r n) 1
        :else (mod (+ (nCr (dec n) (dec r)) (nCr (dec n) r)) 100000007)))

(defn nCr_ [n r]
  (loop [n_ n r_ (min r (- n r)) v 1]
    (if (= r_ 0)
      (int v)
      (recur (dec n_) (dec r_) (mod (/ (* v n_) r_) 100000007)))))



(let [sc (java.util.Scanner. *in*)
      testcases (.nextInt sc)
      nCr (memoize (fn [n r]
                     (loop [n_ n r_ r v 1]
                       (if (= r_ 0)
                         (int v)
                         (recur (dec n_) (dec r_) (mod (/ (* v n_) r_) 100000007))))))
      ]
  (doseq [i (range 0 testcases)]
    (let [no (.nextInt sc) ko (.nextInt sc)]
      (println (nCr no (min ko (- no ko)))))))


(defn pascal [n_]
  (loop [ c '(1) n 1]
    (apply println c)
    (cond 
      (= n_ n) c
      :else
      (recur 
        (for [ i (range 0 (inc n))]
          (+ (nth c (dec i) 0) (nth c i 0))) (inc n)))))



(let [sc (java.util.Scanner. *in*) n__ (.nextInt sc)]
  ((fn [n_]
     (loop [ c '(1) n 1]
       (apply println c)
       (cond 
         (= n_ n) c
         :else
         (recur 
           (for [ i (range 0 (inc n))]
             (+ (nth c (dec i) 0) (nth c i 0))) (inc n))))) n__))

(let [sc (java.util.Scanner. *in*) kv {}]
  (doseq [i (range 0 (.nextInt sc)) v (.nextInt sc)]
    (if (contains? kv v)
      (assoc kv v (inc (get kv v)))
      (assoc kv v 1)))
  (doseq [i (range 0 (.nextInt sc)) v (.nextInt sc)]
    (if (contains? kv v)
      (assoc kv v (dec (get kv v)))
      (assoc kv v -1)))
  (apply println kv))

; leibniz
(let [s (java.util.Scanner. *in*)](doseq [i (range 0 (.nextInt s))](println (format "%.15f" (double(reduce + (take (.nextInt s) (map /(iterate #(* -1 %1) 1) (iterate #(+ 2 %1) 1)))))))))

;def prime_factors(n):
;    """Returns all the prime factors of a positive integer"""
;    factors = []
;    d = 2
;    while n > 1:
;        while n % d == 0:
;            factors.append(d)
;            n /= d
;        d = d + 1
;    return factors
;
;def gcd(a,b):
;    all_prime_facts_a=[]
;    all_prime_facts_b=[]
;    gcd = 1
;    for i in a:
;            all_prime_facts_a=all_prime_facts_a+prime_factors(i)
;    for i in b:
;            all_prime_facts_b=all_prime_facts_b+prime_factors(i)
;
;    for pf in all_prime_facts_a:
;        if pf in all_prime_facts_b:
;            gcd = gcd * pf
;            all_prime_facts_b.remove(pf)
;    print gcd

(defn common [lsta lstb]
  (loop [fl [] l1 lsta l2 lstb]
    (if (or (empty? l1) (empty? l2)) fl
      (cond (= (first l1) (first l2)) (recur (cons (first l1) fl) (rest l1) (rest l2))
            (> (first l1) (first l2)) (recur fl l1 (rest l2))
            (< (first l1) (first l2)) (recur fl (rest l1) l2)))))


;; RMQ 


