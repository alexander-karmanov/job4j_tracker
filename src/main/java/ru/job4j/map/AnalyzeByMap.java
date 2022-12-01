package ru.job4j.map;

import java.util.*;

public class AnalyzeByMap {
    public static double averageScore(List<Pupil> pupils) {
        double quantity = 0;
        double allScore = 0;
        for (Pupil pupil : pupils) {
            for (Subject subject : pupil.subjects()) {
                allScore += subject.score();
                quantity++;
            }
        }
        return allScore / quantity;
    }

    public static List<Label> averageScoreByPupil(List<Pupil> pupils) {
        List<Label> score = new ArrayList<>();
        for (Pupil pupil : pupils) {
            int scoreSum = 0;
            int subjNumb = 0;
            for (Subject subj : pupil.subjects()) {
                scoreSum += subj.score();
                subjNumb++;
            }
            int rsl = scoreSum / subjNumb;
            score.add(new Label(pupil.name(), rsl));
        }
        return score;
    }

    public static List<Label> averageScoreBySubject(List<Pupil> pupils) {
        List<Label> subjects = new ArrayList<>();
        Map<String, Integer> tempMap = new LinkedHashMap<>();
        for (Pupil pupil : pupils) {
            for (Subject subject : pupil.subjects()) {
                int score = tempMap.getOrDefault(subject.name(), 0);
                tempMap.put(subject.name(), score + subject.score());
            }
        }
        for (Map.Entry<String, Integer> entry : tempMap.entrySet()) {
            int score = entry.getValue() / pupils.size();
            subjects.add(new Label(entry.getKey(), score));
        }
        return subjects;
    }

    public static Label bestStudent(List<Pupil> pupils) {
        List<Label> allStudents = new ArrayList<>();
        for (Pupil pupil : pupils) {
            int scoreSum = 0;
            for (Subject subj : pupil.subjects()) {
                scoreSum += subj.score();
            }
            allStudents.add(new Label(pupil.name(), scoreSum));
        }
        allStudents.sort(Comparator.naturalOrder());
        return allStudents.get(allStudents.size() - 1);
    }

    public static Label bestSubject(List<Pupil> pupils) {
        List<Label> subjects = new ArrayList<>();
        Map<String, Integer> tempMap = new LinkedHashMap<>();
        for (Pupil pupil : pupils) {
            for (Subject subject : pupil.subjects()) {
                int score = tempMap.getOrDefault(subject.name(), 0);
                tempMap.put(subject.name(), score + subject.score());
            }
        }
        for (Map.Entry<String, Integer> entry : tempMap.entrySet()) {
            subjects.add(new Label(entry.getKey(), entry.getValue()));
        }
        subjects.sort(Comparator.naturalOrder());
        return subjects.get(subjects.size() - 1);
    }
}
