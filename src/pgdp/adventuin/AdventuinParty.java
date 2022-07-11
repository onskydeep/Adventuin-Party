package pgdp.adventuin;

import java.util.*;
import java.util.stream.Collectors;

import pgdp.color.RgbColor;

public final class AdventuinParty {

    public static Map<HatType, List<Adventuin>> groupByHatType(List<Adventuin> mylist) {
        return mylist.stream().collect(Collectors.groupingBy(x -> x.getHatType()));
    }

    public static void printLocalizedChristmasGreetings(List<Adventuin> mylist) {
        List<Adventuin> sortedList = mylist.stream()
                .sorted(Comparator.comparingInt(Adventuin::getHeight))
                .collect(Collectors.toList());
        sortedList.stream().
                forEach(x -> System.out.println(x.getLanguage().getLocalizedChristmasGreeting(x.getName())));
    }

    public static Map<HatType, List<Adventuin>> getAdventuinsWithLongestNamesByHatType(List<Adventuin> mylist) {
        int maxlen;

        Map<HatType, List<Adventuin>> ans = null;

        List<Adventuin> finallist = null;

        ans = groupByHatType(mylist);

        for (Map.Entry<HatType, List<Adventuin>> entry : ans.entrySet()) {

            maxlen = entry.getValue().
                    stream().mapToInt(x -> x.getName().length()).max().orElse(Integer.MAX_VALUE);

            finallist = new LinkedList<>();

            //finallist filling
            for (Adventuin a : entry.getValue()) {
                if (a.getName().length() == maxlen) finallist.add(a);
            }

            entry.setValue(finallist);

        }

        return ans;

    }

    public static Map<Integer, Double> getAverageColorBrightnessByHeight(List<Adventuin> mylist) {
        Map<Integer, List<Adventuin>> rounded = new HashMap<>();

        rounded = mylist.stream().collect(Collectors.groupingBy(x -> ((x.getHeight() + 5) / 10) * 10));

        Map<Integer, Double> ans = new HashMap<>();

        for (Integer a : rounded.keySet()) {
            double avg = 0.0;
            int len = 0;

            List<Adventuin> that = rounded.get(a);

            for (Adventuin j : that) {

                int R = j.getColor().toRgbColor8Bit().getRed();
                int G = j.getColor().toRgbColor8Bit().getGreen();
                int B = j.getColor().toRgbColor8Bit().getBlue();

                avg += (0.2126 * R + 0.7152 * G + 0.0722 * B) / 255;
                len++;

            }

            avg /= len;

            ans.put(a, avg);
        }

        return ans;
    }


    public static Map <HatType, Double> getDiffOfAvgHeightDiffsToPredecessorByHatType(List <Adventuin> mylist){

        Map <HatType, List<Adventuin>> pings=groupByHatType(mylist);

        Map <HatType, Double> ans= new HashMap<>();

        for (Map.Entry<HatType, List<Adventuin>> entry : pings.entrySet()){

            List <Adventuin> sorted= entry.getValue();

            sorted.stream()
                    .sorted(Comparator.comparingInt(Adventuin::getHeight))
                    .collect(Collectors.toList());

            double negative=0.0;
            double positive=0.0;

            int neglen=0;
            int poslen=0;


             for(int i=0;i<sorted.size()/2;i++){
                    if(entry.getValue().get(i).getHeight() - entry.getValue().get(sorted.size()-i-1).getHeight()<0) {
                        neglen++;
                        negative+=-entry.getValue().get(i).getHeight() + entry.getValue().get(sorted.size()-i-1).getHeight();
                    }
                    if(entry.getValue().get(i).getHeight() - entry.getValue().get(sorted.size()-i-1).getHeight()>0) {
                        poslen++;
                        positive+=entry.getValue().get(i).getHeight() - entry.getValue().get(sorted.size()-i-1).getHeight();
                    }


                }
                if(entry.getValue().get(0).getHeight() - entry.getValue().get(sorted.size()/2-1).getHeight()<0) {
                    neglen++;
                    negative+=-entry.getValue().get(0).getHeight() + entry.getValue().get(sorted.size()-1).getHeight();
                }
                if(entry.getValue().get(0).getHeight() - entry.getValue().get(sorted.size()/2-1).getHeight()>0) {
                    poslen++;
                    positive+=entry.getValue().get(0).getHeight() - entry.getValue().get(sorted.size()/2-1).getHeight();
            }




            double AVERAGE=0.0;

            AVERAGE=negative/neglen+positive/poslen;

            ans.put(entry.getKey(), AVERAGE);
        }
        return ans;

    }

}
