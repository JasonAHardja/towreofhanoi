import java.util.Scanner;

public class towerofhanoi {
    static Scanner scanner = new Scanner(System.in);
    static int moves = 0;

    public static void main(String[] args) {
        System.out.print("enter the number of disks: ");
        int disks = scanner.nextInt();

        System.out.println("disk sizes: small (1), medium (2), big (3)");

        System.out.println("steps: ");
        Tower[] towers = initializeTowers(disks);

        displayTowers(towers);

        solveHanoi(disks, towers[0], towers[1], towers[2]);

        System.out.println("total moves: " + moves);
    }

    static Tower[] initializeTowers(int disks) {
        Tower[] towers = new Tower[3];
        towers[0] = new Tower("A");
        towers[1] = new Tower("B");
        towers[2] = new Tower("C");

        for (int i = disks; i >= 1; i--) {
            towers[0].push(new Disk(i));
        }

        return towers;
    }

    static void displayTowers(Tower[] towers) {
        for (Tower tower : towers) {
            tower.display();
        }
        System.out.println("-------------");
    }

    static void solveHanoi(int disks, Tower source, Tower auxiliary, Tower destination) {
        if (disks == 1) {
            moveDisk(source, destination);
            moves++;
            displayTowers(new Tower[]{source, auxiliary, destination});
            return;
        }

        solveHanoi(disks - 1, source, destination, auxiliary);
        moveDisk(source, destination);
        moves++;
        displayTowers(new Tower[]{source, auxiliary, destination});
        solveHanoi(disks - 1, auxiliary, source, destination);
    }

    static void moveDisk(Tower source, Tower destination) {
        Disk disk = source.pop();
        if (destination.isEmpty() || destination.peek().size >= disk.size) {
            destination.push(disk);
        } else {
            System.out.println("Invalid move: Smallest disk cannot be placed on top of larger disk");
            source.push(disk);
        }
    }
}
class Tower {
    private String name;
    private java.util.Stack<Disk> disks;

    public Tower(String name) {
        this.name = name;
        this.disks = new java.util.Stack<>();
    }

    public void push(Disk disk) {
        disks.push(disk);
    }

    public Disk pop() {
        return disks.pop();
    }

    public Disk peek() {
        return disks.peek();
    }

    public boolean isEmpty() {
        return disks.isEmpty();
    }

    public void display() {
        System.out.println("Tower " + name + ": " + disks);
    }
}

class Disk {
    public int size;

    public Disk(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return String.valueOf(size);
    }
}


