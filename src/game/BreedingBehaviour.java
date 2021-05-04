package game;

import edu.monash.fit2099.engine.*;

import java.util.HashMap;

public class BreedingBehaviour implements Behaviour {

    private HashMap<Integer, Dinosaur> dinosaurs = new HashMap<>();
    @Override
    public Action getAction(Actor actor, GameMap map) {
        for (int x = 0; x < 80; x++){
            for (int y = 0; y < 25; y++){
                Actor target = map.at(x, y).getActor();

                if (target != null && (target.toString().equals(actor.toString()))){
                    Dinosaur dinosaurTarget = (Dinosaur) target;
                    Dinosaur dinosaur = (Dinosaur) actor;
                    if (!dinosaur.getGender().equals(dinosaurTarget.getGender())){
                        int distance = new FollowBehaviour(actor).distance(map.locationOf(dinosaur), map.locationOf(dinosaurTarget));
                        dinosaurs.put(distance, dinosaurTarget);
                    }

                }

            }
        }

        if (dinosaurs.size() != 0) {
            int lowestDistance = (int) dinosaurs.keySet().toArray()[0];
            for (int keys : dinosaurs.keySet()) {
                if (keys < lowestDistance) {
                    lowestDistance = keys;
                }
            }
            if (new FollowBehaviour(dinosaurs.get(lowestDistance)).getAction(actor,map) != null){
                return new FollowBehaviour(dinosaurs.get(lowestDistance)).getAction(actor,map);
            }
            else{
                for (Exit exits:map.locationOf(actor).getExits()){
                    if (exits.getDestination() == map.locationOf(dinosaurs.get(lowestDistance))){
                        return new BreedingAction(dinosaurs.remove(lowestDistance));
                    }
                }
                return new DoNothingAction();
            }
            /*else if (new FollowBehaviour(stegosaurs.get(lowestDistance)).location.getActor().toString().equals("Stegosaur")){
                return new AttackAction(stegosaurs.get(lowestDistance));
            }*/
        }
        return new DoNothingAction();
    }
}
