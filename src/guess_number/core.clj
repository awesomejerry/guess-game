(ns guess-number.core
  (:gen-class))

(defn parse-int [x] (Integer/parseInt x))
(defn enter-max [] (parse-int (do (print "Please enter maximum number: ")
                                  (flush)
                                  (read-line))))
(defn enter-answer [] (parse-int (do (print "Please enter your answer: ")
                                     (flush)
                                     (read-line))))
(defn verify-answer [answer max equal]
  (let [input (enter-answer)]
    (if (= answer input)
      (equal)
      (do
        (if (> answer input)
          (println (str "It's between " input " and " max))
          (println (str "It's between 0 and " input)))
        (verify-answer answer max equal)))))


(defn -main
  [& args]
  (let [max (enter-max)
        answer (rand-int max)]
    (verify-answer answer max #(println "You hit the answer!"))))
