(ns guess-number.core
  (:gen-class))

(defn not-a-valid-number [] (println "Not a valid number"))
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
