(seq (range 0 10))

(def lst [-20 1 20 -10])

(for [l lst]
  (if (< l 0)
    (* -1 l)
    (* 1 l)))

(defn rev [lst] (cons (last lst) (rev (butlast lst))))

 (fn [lst] 
     (- (reduce #(+ % 1) lst) (reduce + lst)))