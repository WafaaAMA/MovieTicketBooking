/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

  

package javaapplication66;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

// --- Observer Pattern ---
// Observer Interface
interface TicketObserver {
    void update(String ticketDetails);
}

// Subject Class (Observable)
class TicketBookingSystemObservable {
    private List<TicketObserver> observers = new ArrayList<>();
    private String ticketDetails;

    public void addObserver(TicketObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(TicketObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (TicketObserver observer : observers) {
            observer.update(ticketDetails);
        }
    }

    public void bookTicket(String ticketDetails) {
        this.ticketDetails = ticketDetails;
        notifyObservers();
    }
}

// --- Factory Pattern for Movie Types ---

abstract class Movie {
    String genre;
}

class ActionMovie extends Movie {
    public ActionMovie() {
        this.genre = "Action";
    }
}

class ComedyMovie extends Movie {
    public ComedyMovie() {
        this.genre = "Comedy";
    }
}

class DramaMovie extends Movie {
    public DramaMovie() {
        this.genre = "Drama";
    }
}

class MovieFactory {
    public static Movie createMovie(String genre) {
        switch (genre) {
            case "Action":
                return new ActionMovie();
            case "Comedy":
                return new ComedyMovie();
            case "Drama":
                return new DramaMovie();
            default:
                return null;
        }
    }
}

// --- Factory Pattern for Theater Types ---

abstract class TheaterType {
    String name;

    public String getName() {
        return name;
    }
}

class CinemaHall extends TheaterType {
    public CinemaHall() {
        this.name = "Cinema Hall";
    }
}

class IMAX extends TheaterType {
    public IMAX() {
        this.name = "IMAX";
    }
}

class TheaterFactory {
    public static TheaterType createTheater(String type) {
        switch (type) {
            case "Cinema Hall":
                return new CinemaHall();
            case "IMAX":
                return new IMAX();
            default:
                return null;
        }
    }
}

// --- Singleton for Ticket Booking System ---

class TicketBookingSystem {
    private static TicketBookingSystem instance;

    private TicketBookingSystem() {}

    public static TicketBookingSystem getInstance() {
        if (instance == null) {
            instance = new TicketBookingSystem();
        }
        return instance;
    }

    public void bookTicket(String movieName, String theaterType, String genre, int seats) {
        JOptionPane.showMessageDialog(null, "Booking confirmed for " + movieName + " at " + theaterType + ".\nGenre: " + genre + ", Seats: " + seats);
    }
}

// --- Builder Pattern ---

class Ticket {
    private String movieName;
    private String theaterType;
    private String genre;
    private int seats;
    private double price;

    public Ticket(String movieName, String theaterType, String genre, int seats, double price) {
        this.movieName = movieName;
        this.theaterType = theaterType;
        this.genre = genre;
        this.seats = seats;
        this.price = price;
    }

    public String getMovieName() {
        return movieName;
    }

    public String getTheaterType() {
        return theaterType;
    }

    public String getGenre() {
        return genre;
    }

    public int getSeats() {
        return seats;
    }

    public double getPrice() {
        return price;
    }

    public String getDetails() {
        return "Movie: " + movieName + "\nTheater: " + theaterType + "\nGenre: " + genre + "\nSeats: " + seats + "\nPrice: $" + price;
    }
}

// --- Prototype Pattern ---
// The Ticket class implements the Prototype interface
class TicketPrototype implements Cloneable {
    private String movieName;
    private String theaterType;
    private String genre;
    private int seats;
    private double price;

    public TicketPrototype(String movieName, String theaterType, String genre, int seats, double price) {
        this.movieName = movieName;
        this.theaterType = theaterType;
        this.genre = genre;
        this.seats = seats;
        this.price = price;
    }

    public String getMovieName() {
        return movieName;
    }

    public String getTheaterType() {
        return theaterType;
    }

    public String getGenre() {
        return genre;
    }

    public int getSeats() {
        return seats;
    }

    public double getPrice() {
        return price;
    }

    public String getDetails() {
        return "Movie: " + movieName + "\nTheater: " + theaterType + "\nGenre: " + genre + "\nSeats: " + seats + "\nPrice: $" + price;
    }

    // Prototype - Cloning method
    public TicketPrototype clone() {
        try {
            return (TicketPrototype) super.clone();  // Return a clone of the current Ticket object
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }
}

// --- GUI Application ---

public class MovieTicketBookingGUI implements TicketObserver {
    public static void main(String[] args) {
        final JFrame frame = new JFrame("Movie Ticket Booking System");
        frame.setSize(600, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout(10, 10));

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(5, 2, 10, 10));

        JLabel lblMovieName = new JLabel("Movie Name:");
        final JTextField txtMovieName = new JTextField();

        JLabel lblTheaterType = new JLabel("Theater Type:");
        final JComboBox<String> cbTheaterType = new JComboBox<>(new String[]{"Cinema Hall", "IMAX"});

        JLabel lblGenre = new JLabel("Genre:");
        final JComboBox<String> cbGenre = new JComboBox<>(new String[]{"Action", "Comedy", "Drama"});

        JLabel lblSeats = new JLabel("Number of Seats:");
        final JTextField txtSeats = new JTextField();

        inputPanel.add(lblMovieName);
        inputPanel.add(txtMovieName);
        inputPanel.add(lblTheaterType);
        inputPanel.add(cbTheaterType);
        inputPanel.add(lblGenre);
        inputPanel.add(cbGenre);
        inputPanel.add(lblSeats);
        inputPanel.add(txtSeats);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        JButton btnBook = new JButton("Book Ticket");
        JButton btnBuild = new JButton("Build Ticket");
        JButton btnClone = new JButton("Clone Ticket");
        JButton btnClear = new JButton("Clear");

        buttonPanel.add(btnBook);
        buttonPanel.add(btnBuild);
        buttonPanel.add(btnClone);
        buttonPanel.add(btnClear);

        JPanel outputPanel = new JPanel();
        outputPanel.setLayout(new GridLayout(1, 2, 10, 10));

        final JTextArea ticketDetails = new JTextArea();
        ticketDetails.setEditable(false);
        ticketDetails.setBorder(BorderFactory.createTitledBorder("Ticket Details"));

        final JTextArea clonedTicketDetails = new JTextArea();
        clonedTicketDetails.setEditable(false);
        clonedTicketDetails.setBorder(BorderFactory.createTitledBorder("Cloned Ticket Details"));

        outputPanel.add(new JScrollPane(ticketDetails));
        outputPanel.add(new JScrollPane(clonedTicketDetails));

        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.CENTER);
        frame.add(outputPanel, BorderLayout.SOUTH);

        final TicketPrototype[] currentTicket = new TicketPrototype[1];

        // Create Observable (Subject)
        final TicketBookingSystemObservable observable = new TicketBookingSystemObservable();
        observable.addObserver(new MovieTicketBookingGUI());

        btnBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String movieName = txtMovieName.getText();
                String theaterType = cbTheaterType.getSelectedItem().toString();
                String genre = cbGenre.getSelectedItem().toString();
                int seats = Integer.parseInt(txtSeats.getText());

                // Use Factory to create TheaterType
                TheaterType theater = TheaterFactory.createTheater(theaterType);

                TicketBookingSystem.getInstance().bookTicket(movieName, theater.getName(), genre, seats);
                currentTicket[0] = new TicketPrototype(movieName, theater.getName(), genre, seats, seats * 10.0);  // السعر محسوب هنا

                // Notify observers about the new ticket
                observable.bookTicket(currentTicket[0].getDetails());
            }
        });

        btnBuild.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentTicket[0] != null) {
                    ticketDetails.setText(currentTicket[0].getDetails());
                } else {
                    JOptionPane.showMessageDialog(frame, "No ticket booked yet!");
                }
            }
        });

        btnClone.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentTicket[0] != null) {
                    TicketPrototype clonedTicket = currentTicket[0].clone();
                    clonedTicketDetails.setText(clonedTicket.getDetails());
                } else {
                    JOptionPane.showMessageDialog(frame, "No ticket to clone!");
                }
            }
        });

        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtMovieName.setText("");
                txtSeats.setText("");
                cbTheaterType.setSelectedIndex(0);
                cbGenre.setSelectedIndex(0);
                ticketDetails.setText("");
                clonedTicketDetails.setText("");
            }
        });

        frame.setVisible(true);
    }

   
    public void update(String ticketDetails) {
        
        JOptionPane.showMessageDialog(null, "تم تحديث تفاصيل التذكرة: \n" + ticketDetails);
    }
}
