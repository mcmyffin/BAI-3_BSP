package mk.Verraucher;

import mk.Erzeuger.Student;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;

import java.util.concurrent.locks.ReentrantLock;



/**
 *
 * @author dima
 */
public class Kasse extends Thread{
    
    private Queue<Student> queue;
    private ReentrantLock zugriffMutex;
    private Semaphore     queueSemaphore;
    private Condition     waitingQueue;
    
    private int kassenNummer;
    
    public Kasse(int kassenNummer){
        
        queue = new LinkedList();
        zugriffMutex = new ReentrantLock(true);
        queueSemaphore = new Semaphore(0, true);
        this.kassenNummer = kassenNummer;
        this.waitingQueue = zugriffMutex.newCondition();
    }
    
    
    public int getQueueSize(){
        zugriffMutex.lock();
        int size = queue.size();
        zugriffMutex.unlock();
        
        return size;
    } 
    
    public void anKasseAnstellen(Student student){
            zugriffMutex.lock();
                queue.add(student);
                queueSemaphore.release();
                student.anstellen();
                System.err.println(student+" stellt sich an Kasse "+kassenNummer+" an");
                
                while(!student.hatBezahlt()){
                
                    try {
                        waitingQueue.await();
                    } catch (InterruptedException ex) {
                        System.err.println(student+" wurde in der aus der Warteschlage rausgeschmissen!");
                        student.interrupt();
                        break;
                    }
                }
                
            zugriffMutex.unlock();   
    }
    
    
    public void run(){
        
        while(!this.isInterrupted()){
            
            try {
                queueSemaphore.acquire();
                zugriffMutex.lock();
                
                System.err.println("Kasse "+kassenNummer+" guckt auf Warteschlange "+queue.toString());
                Student student = queue.poll();
                System.err.println("Kasse "+kassenNummer+" bedient "+student);
                
                student.bezahlen();
                System.err.println("Kasse "+kassenNummer+" hat jetzt noch "+queue.size()+" wartende Studenten");    
                
                waitingQueue.signalAll();
                zugriffMutex.unlock();
                
            } catch (InterruptedException ex) {
                System.err.println("== Kasse "+kassenNummer+" wird beim Bezahlvorgang unterbrochen ==");
                this.interrupt();
            }
        }
    }

    @Override
    public String toString() {
        return kassenNummer+"";
    }
    
    
}
