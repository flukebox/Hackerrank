(ns clojure-learn.core)

(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!"))

(defn sum 
  "Sum given two numbers"
  [ x y]
  (+ x y))

;;================================ Traversion words to words with one hop =======================;;

(def dictionary 
  (->> (slurp "/usr/share/dict/words")
    clojure.string/split-lines
    (map clojure.string/lower-case)
    (into #{})))

(def alphabet "abcdefghijklmnopqrstuvwxyz")

(defn edits [^String word]
  "Returns words that differ from word by one letter. E.g., 
  cat => fat, cut, can, etc."
  (->> word
    (map-indexed (fn [i c]
                   (let [sb (StringBuilder. word)]
                     (for [altc alphabet :when (not= altc c)]
                       (str (doto sb (.setCharAt i altc )))))))
    (apply concat)
    (filter dictionary)))

(defn find-path [neighbors start end]
  "Return a path from start to end with the fewest hops (i.e irrespective 
  of edge weights), neighbors being a function that returns adjacent nodes"
  (loop [queue (conj clojure.lang.PersistentQueue/EMPTY start)
         preds {start nil}] 
    (when-let [node (peek queue)]
      (let [nbrs (remove #(contains? preds %) (neighbors node))]
        (if (some #{end} nbrs)
          (reverse (cons end (take-while identity (iterate preds node))))
          (recur (into (pop queue) nbrs)
                 (reduce #(assoc %1 %2 node) preds nbrs)))))))

;;===============================================================================================;;
(use '[clojure.java.shell :only [sh]])
(sh "ls" "-aul1h")

(def vowel? (set "aieou"))

(defn pig-latin [^String word] 
  (let [first-letter (first word)] 
    (if (vowel? first-letter)
      (str word "ay")
      (str (subs word 1) first-letter "ay"))))

(def ^:dynamic v 1)

(defn f1 [] 
  (println "f1:v=" v))

(defn f2 []
  (println "f2:before let v=" v)
  (let [v 2]
    (println "f2:in let v=" v)
    (f1))
  (println "f2:after let v=" v))

(defn f3 [] 
  (println "f3:before binding v=" v)
  (binding [v 3]
    (println "f3:in binding function v=" v)
    (f1))
  (println "f3:after binding v=" v))

(defn f4 []
  (def v 4))

(defn polynomial
  "Compute value of a polynomial with given coefficient and given value of x"
  [coefs x]
  (let [expo (reverse (range (count coefs)))]
    (apply + (map #(* %1  (java.lang.Math/pow x %2)) coefs expo))))

(defn derivative
  "Compute value of the derivative of a polynomial with given coefficients and given value of x"
  [coefs x]
  (let [expo (reverse (range (count coefs)))
        derivative-coefs (map #(* %1 %2) (butlast coefs) expo)]
    (polynomial derivative-coefs x)))

(def f (partial polynomial [2 1 3])) ; 2x^2 + x + 3

(def f-prime (partial derivative [2 1 3])); 4x + 1

(def memo-f (memoize f))

(def first-ten 
  (->> (range)
    (map #(* % %))
    (filter even?)
    (take 10)
    (reduce +)))

(def first-ten2 
  (reduce + 
          (take 10 
                (filter even?
                        (map #(* % %)
                             (range))))))


(defn delayed-print [ms text]
  (Thread/sleep ms)
  (println text))


(defn average [coll]
  (when-not (collection? coll)
    (throw (IllegalArgumentException. "expected a collection")))
  (when (empty? coll)
    (throw (IllegalArgumentException. "collection is empty")))
  (let [sum (apply + coll)]
    (/ sum (count coll))))

(let [gc (GregorianCalendar.) 
      day-of-the-week (.get gc Calendar/DAY_OF_WEEK)
      is-weekend (or (= day-of-the-week Calendar/SATURDAY) (= day-of-the-week Calendar/SUNDAY))]
  (if is-weekend
    (println "Play")
    (do (println "Work")
      (println "Sleep"))))


(let [gc (GregorianCalendar.) 
      day-of-the-week (.get gc Calendar/DAY_OF_WEEK)
      is-weekend (or (= day-of-the-week Calendar/SATURDAY) (= day-of-the-week Calendar/SUNDAY))]
  (when is-weekend (println "Play"))
  (when-not is-weekend (println "Work") (println "Sleep")))

(defn process-next [waiting-line] 
  (if-let [name (first waiting-line)]
    (println name "is next")
    (println "no waiting")))

(defn summarize 
  [coll]
  (when-let [head (first coll)]
    (print head)
    (dotimes [_ (dec (count coll))] (print \.))
    (println)))

;; IO Work 
;(println "Enter a number:") 
;(flush) 
;(let [reader (java.io.BufferedReader. *in*)
;      line (.readLine reader)
;      value (try 
;              (Integer/parseInt line)
;              (catch NumberFormatException e line))]
;  (println 
;    (condp = value
;      1 "One"
;      2 "Two"
;      3 "Three"
;      (str "unexpected value, \"" value \")))
;  (println 
;    (condp instance? value
;      Number (* value 2)
;      String (* (count value) 2))))

(defn my-fn [ms]
  (println "entered my-fn")
  (Thread/sleep ms)
  (println "leaving my-fn"))

(let [thread (Thread. #(my-fn 2))]
  (.start thread)
  (println "started thread")
  (while (.isAlive thread)
    (print ".")
    (flush))
  (println "thread stopped"))

(def cols "ABCD")
(def rows (range 1 4))

(println "\nfor demo")
(dorun
  (for [col cols :when (not= col \B)
        row rows :while (< row 3)]
    (println (str row col))))

(println "\ndoseq demo")
(doseq [col cols :when (not= col \B)
        row rows :while (< row 3)]
  (println (str row col)))

(defn factorial-1 [number]
  (loop [n number factorial 1]
    (if (zero? n)
      factorial
      (recur (dec n) (* factorial n)))))

(def prime 100000007)
(defn fib [a b] (cons a (lazy-seq (fib b (mod (+ b a) prime)))))
(def fib-seq-n (take 10001 (fib  0 1 )))

(let [sc (java.util.Scanner. *in*)
      testcases (.nextInt sc)]
  (doseq [i (range 0 testcases)]
        (println (nth fib-seq-n  (.nextInt sc)))))
  
  
  ;; IO Work 
  ;(println "Enter a number:") 
  ;(flush) 
  ;(let [reader (java.io.BufferedReader. *in*)
  ;      line (.readLine reader)
  ;      value (try 
  ;              (Integer/parseInt line)
  ;              (catch NumberFormatException e line))]
  ;  (println 
  ;    (condp = value
  ;      1 "One"
  ;      2 "Two"
  ;      3 "Three"
  ;      (str "unexpected value, \"" value \")))
  ;  (println 
  ;    (condp instance? value
  ;      Number (* value 2)
  ;      String (* (count value) 2))))
  
  
  
  
  (defn f 
    "square the given argument and divide by 2"
    [x]
    (println (str "calculating f of " x "\n"))
    (/ (* x x) 2.0))
  
  ; Create an infinite sequence
  (def f-seq (map f (iterate inc 0)))
  
  ; Function for infinite sequence on the go
  (defn f-seq2 [] (map f (iterate inc 0)))
  
  ; Consumer
  (defn consumer [seq]
    (println (first seq))
    (println (nth seq 10)))
  
  (let [obj1 "Me"
        obj2 {:name "Jai" :number Math/PI}]
    (println "output from print")
    (print obj1 obj2)
    (println "output from println")
    (println obj1 obj2)
    (println "output from pr")
    (pr obj1 obj2)
    (println "output from prn")
    (prn obj1 obj2))
  
  
  (use '[clojure.java.io :only (reader)])
  
  (defn print-only-if-contains [line word]
    (when (.contains line word) (println line)))
  
  (let [file "mylog.log"
        word "goes" ]
    (with-open [rdr (reader file)]
      (doseq [line (line-seq rdr)] 
        (print-only-if-contains line word))))
  
  
  (defn summer-sales-percentage 
    [{june :june july :july august :august :as all}]
    (let [summer-sales (+ june july august)
          all-sales (apply + (vals all))]
      (/ summer-sales all-sales)))
  
  (def sales {:jan 100 :feb 200 :march 0 :april 300
              :may 200 :june 100 :july 400 :august 500
              :sep 200 :oct 300 :nov 400 :dec 600 })
  (summer-sales-percentage sales)
  
  
  (defmacro around-zero [number negative-expr zero-expr positive-expr]
    `(let [number# ~number]
       (cond
         (< (Math/abs number#) 1e-15) ~zero-expr
         (pos? number#) ~positive-expr
         true? ~negative-expr )))
  
  (defn number-category [number]
    (around-zero number "negative" "zero" "positive"))
  
  (defmacro trig-y-category [fn degrees]
    `(let [radians# (java.lang.Math/toRadians ~degrees)
           result# (~fn radians#)]
       (number-category result#)))
  
  (doseq [angle (range 0 360 90)]
    (println (trig-y-category Math/sin angle)))
    
  