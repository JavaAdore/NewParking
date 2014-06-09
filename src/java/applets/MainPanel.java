package applets;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import pojo.Garage;
import pojo.Map;
import utils.CurrentSelectedToggleButton;


public class MainPanel extends javax.swing.JPanel {

    private int imageWidth;
   
    private int imageHeight;
    
    private LoadMapsDialog dialog;
    
    private Garage garage;
    
    private Map map;
    
    private DrawingTool parent;
    
    private Image originalImage;
    
    private ButtonGroup  bg ; 
    
    private CurrentSelectedToggleButton cstb = CurrentSelectedToggleButton.NONE; 

    private Image scaled ;
    
    private DrawingPanel drawingPanel; 

    
    
    public MainPanel(JApplet parent) {

        initComponents();

        initButtonListeners();

        initSlider();

        initToggles(); 

        //displayPanel.setLayout(new GridLayout());
    
        this.parent = (DrawingTool) parent;

        drawingPanel = new DrawingPanel();
        
        displayPanel.add(drawingPanel);
        
    }

    private void initButtonListeners() {

        LoadMapButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                parent.showGlassPane();

                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        dialog = new LoadMapsDialog(null, true, MainPanel.this);

                        parent.hideGlassPane();

                        dialog.setVisible(true);

                    }
                }).start();

            }
        });

        incButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int val = sizeSlider.getValue() + 5;
                scaleImageAndUpdate(calcDimensions(val));
                sizeSlider.setValue(val);
            }
        });

        decButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int val = sizeSlider.getValue() - 5;
                scaleImageAndUpdate(calcDimensions(val));
                sizeSlider.setValue(val);
            }
        });

    }

    private void initToggles() {

        bg = new ButtonGroup() ;
        
        bg.add(doorButton);
        
        bg.add(slotButton);
        
        bg.add(lineButton);
        
        ActionListener actionListener = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if(e.getSource() == doorButton){
                    
                    cstb = CurrentSelectedToggleButton.DOOR ; 
                }
                else if(e.getSource() == slotButton){
                    
                    cstb = CurrentSelectedToggleButton.SLOT; 
                }
                else{
                    
                    cstb = CurrentSelectedToggleButton.LINE ; 
                }
                
             //   displayingLabel.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
            }
        }; 
        
        doorButton.addActionListener(actionListener);
        
        slotButton.addActionListener(actionListener);
        
        lineButton.addActionListener(actionListener);
        
    }
    
    private void initSlider() {

        sizeSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {

                if (originalImage != null) {
                    int val = sizeSlider.getValue();

                    Point p = calcDimensions(val);

                    scaleImageAndUpdate(p);
                }
            }
        });
    }

    
    
    private Point calcDimensions(int percent) {

        int width = 5, height = 5;

        if (percent > 0) {

            width = (imageWidth * percent) / 100;

            height = (imageHeight * percent) / 100;

        }
        return new Point(width, height);
    }

    private void scaleImageAndUpdate(Point p) {

        if (originalImage != null) {

            scaled = originalImage.getScaledInstance(p.x, p.y, Image.SCALE_FAST);
        
            drawingPanel.setPreferredSize(new Dimension(scaled.getWidth(drawingPanel), scaled.getHeight(drawingPanel)));
         
            drawingPanel.repaint();
            
        }
    }
    
    
    public void setGarage(Garage garage) {
        this.garage = garage;
    }

    public void setMap(Map map) {

        this.map = map;

        imageWidth = map.getWidth();

        imageHeight = map.getHeight();

        try {

            ImageIcon icon = new ImageIcon(new URL("http://localhost:9090/Parking/images/" + map.getMapUrl()));

            originalImage = icon.getImage();

            scaleImageAndUpdate(calcDimensions(50));

        } catch (MalformedURLException ex) {
            Logger.getLogger(MainPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    
    public Garage getGarage() {
        return garage;
    }

    public Map getMap() {
        return map;
    }

    
//    public class HandScrollListener extends MouseAdapter {
//
//        private final Cursor defCursor = Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR);
//        private final Cursor hndCursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
//        private final Point pp = new Point();
//        private JLabel image;
//
//        public HandScrollListener(JLabel image) {
//            this.image = image;
//        }
//
//        public void mouseDragged(final MouseEvent e) {
//            JViewport vport = (JViewport) e.getSource();
//            Point cp = e.getPoint();
//            Point vp = vport.getViewPosition();
//            vp.translate(pp.x - cp.x, pp.y - cp.y);
//            image.scrollRectToVisible(new Rectangle(vp, vport.getSize()));
//            pp.setLocation(cp);
//        }
//
//        public void mousePressed(MouseEvent e) {
//            image.setCursor(hndCursor);
//            pp.setLocation(e.getPoint());
//        }
//
//        public void mouseReleased(MouseEvent e) {
//            image.setCursor(defCursor);
//            image.repaint();
//        }
//    }

    
    class DrawingPanel extends JPanel{

        @Override
        public void paintComponent(Graphics g) {

            super.paintComponent(g);
            g.drawImage(scaled  , 0 , 0, this);
       
        }
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        displayPanel = new javax.swing.JPanel();
        sizeSlider = new javax.swing.JSlider();
        incButton = new javax.swing.JButton();
        decButton = new javax.swing.JButton();
        generateDataButton = new javax.swing.JButton();
        clearDataButton = new javax.swing.JButton();
        LoadMapButton = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        slotButton = new javax.swing.JToggleButton();
        doorButton = new javax.swing.JToggleButton();
        lineButton = new javax.swing.JToggleButton();
        jLabel2 = new javax.swing.JLabel();

        displayPanel.setBackground(new java.awt.Color(255, 255, 255));
        displayPanel.setLayout(new java.awt.GridLayout());
        jScrollPane1.setViewportView(displayPanel);

        sizeSlider.setMajorTickSpacing(20);
        sizeSlider.setMinorTickSpacing(5);
        sizeSlider.setPaintLabels(true);
        sizeSlider.setPaintTicks(true);
        sizeSlider.setSnapToTicks(true);

        incButton.setText("+");
        incButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                incButtonActionPerformed(evt);
            }
        });

        decButton.setText("-");

        generateDataButton.setText("Generate Data");

        clearDataButton.setText("Clear Data");

        LoadMapButton.setText("Load Garage Map");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 204, 153));
        jLabel1.setText("Door");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 204, 153));
        jLabel3.setText("Slot");

        slotButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/applets/slot.png"))); // NOI18N

        doorButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/applets/door.png"))); // NOI18N

        lineButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/applets/line.png"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 204, 153));
        jLabel2.setText("Line");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(slotButton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(doorButton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1)))
                            .addComponent(lineButton, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(jLabel2)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(slotButton)
                    .addComponent(doorButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3))
                .addGap(38, 38, 38)
                .addComponent(lineButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addContainerGap(71, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(generateDataButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(clearDataButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(LoadMapButton, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 981, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(decButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sizeSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 523, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(incButton)
                .addGap(253, 253, 253))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(LoadMapButton)
                        .addGap(64, 64, 64)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(generateDataButton, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(clearDataButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sizeSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(incButton)
                    .addComponent(decButton)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void incButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_incButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_incButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton LoadMapButton;
    private javax.swing.JButton clearDataButton;
    private javax.swing.JButton decButton;
    private javax.swing.JPanel displayPanel;
    private javax.swing.JToggleButton doorButton;
    private javax.swing.JButton generateDataButton;
    private javax.swing.JButton incButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToggleButton lineButton;
    private javax.swing.JSlider sizeSlider;
    private javax.swing.JToggleButton slotButton;
    // End of variables declaration//GEN-END:variables
}


