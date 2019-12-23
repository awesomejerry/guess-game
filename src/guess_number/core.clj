(ns guess-number.core
  (:gen-class))

(defn not-a-valid-number [] (println "Not a valid number"))
(defn is-between-numbers [min max] (println (str "It's between " min " and " max)))
(defn parse-int [x] (Integer/parseInt x))
(defn get-user-input [message] (do (print message) (flush) (read-line)))
(defn enter-max []
  (let [input (get-user-input "Please enter maximum number: ")]
    (try (parse-int input)
         (catch Exception e
           (do
             (not-a-valid-number)
             (enter-max))))))
(defn enter-answer []
  (let [input (get-user-input "Please enter your answer: ")]
    (try (parse-int input)
         (catch Exception e
           (do
             (not-a-valid-number)
             (enter-answer))))))
(defn verify-answer [answer min max equal]
  (let [input (enter-answer)]
    (if (= answer input)
      (equal)
      (if (> answer input)
        (do (is-between-numbers input max)
            (verify-answer answer input max equal))
        (do (is-between-numbers min input)
            (verify-answer answer min input equal))))))

(defn -main
  [& args]
  (let [max (enter-max)
        answer (rand-int (inc max))]
    (verify-answer answer 0 max #(println "You hit the answer!"))))
