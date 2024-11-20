// package Tests;

// import org.junit.Assert;
// import org.junit.Test;

// import models.Drive;
// import models.File;
// import models.Folder;
// import models.User;

// public class RelationshipTest {
    
//     @Test
//     public void userOwnMultipleDrivesTest() {
//         User Sinh = new User("Sinh");

//         Drive oneDrive = new Drive("One Drive", Sinh);
//         Drive googleDrive = new Drive("Google Drive", Sinh);

//         Sinh.addDrive(googleDrive);
//         Sinh.addDrive(oneDrive);

//         Assert.assertEquals(2, Sinh.getDrives().size());

//         Assert.assertEquals(Sinh, googleDrive.getOwner());
//         Assert.assertEquals(Sinh, oneDrive.getOwner());  
//     }

//     @Test
//     public void driveHasMultipleItemsTest() {
//         User Sinh = new User("Sinh");

//         Drive googleDrive = new Drive("Google Drive");

//         Folder designDocument = new Folder("Design Documents");
//         Folder developmentFiles = new Folder("Development Files");
//         File marketingMaterials = new File("Marketing Materials.pdf");

//         Sinh.addDrive(googleDrive);

//         googleDrive.addItem(designDocument);
//         googleDrive.addItem(developmentFiles);
//         googleDrive.addItem(marketingMaterials);
        
//         Assert.assertEquals(3, googleDrive.getChildItems().size());

//         Assert.assertEquals(googleDrive, designDocument.getParentStore());
//         Assert.assertEquals(googleDrive, developmentFiles.getParentStore());
//         Assert.assertEquals(googleDrive, marketingMaterials.getParentStore());
//     }

//     @Test
//     public void folderHasMultipleItemsTest() {
//         User Sinh = new User("Sinh");
//         Drive googleDrive = new Drive("Google Drive");

//         Folder designDocument = new Folder("Design Documents");

//         Folder wireframes = new Folder("Wireframes");
//         Folder prototypes = new Folder("Prototypes");
//         File mockup = new File("Mockup.png");

//         Sinh.addDrive(googleDrive);
//         googleDrive.addItem(designDocument);

//         designDocument.addItem(wireframes);
//         designDocument.addItem(prototypes);
//         designDocument.addItem(mockup);

//         Assert.assertEquals(3, designDocument.getChildItems().size());

//         Assert.assertEquals(designDocument, wireframes.getParentStore());
//         Assert.assertEquals(designDocument, prototypes.getParentStore());
//         Assert.assertEquals(designDocument, mockup.getParentStore());
//     }
// }