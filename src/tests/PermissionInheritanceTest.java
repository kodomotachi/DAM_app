package tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import roles.Role;
import models.Drive;
import models.File;
import models.Folder;
import models.User;

public class PermissionInheritanceTest {

    private User Alex, Jordan, Taylor;
    private Drive oneDrive, googleDrive;

    private Folder designDocuments, developmentFiles;
    private File marketingMaterials;

    private Folder wireframes, prototypes;
    private File mockUp;

    private Folder initialDrafts;
    private File blueprints;

    @Before
    public void setUpUserDrive() {
        addDrivesToUser();
        addFolersAndFilesToDrive();
        addFoldersAndFilesToFolder();
        addFoldersAndFilesToSubfolder();
    }

    private void addDrivesToUser() {
        Alex = new User("alex@gmail.com","123");
        Jordan = new User("jordan@gmail.com", "345");
        Taylor = new User("taylor@gmail.com", "678");

        googleDrive = new Drive("Google Drive");
        oneDrive = new Drive("One Drive");

        Alex.addDrive(googleDrive);
        Alex.addDrive(oneDrive);
    }

    private void addFolersAndFilesToDrive() {
        designDocuments = new Folder("Design Documents");
        developmentFiles = new Folder("Development Files");
        marketingMaterials = new File("Marketing Materials.pdf");

        googleDrive.addStore(designDocuments);
        googleDrive.addStore(developmentFiles);
        googleDrive.addStore(marketingMaterials); 
    }

    private void addFoldersAndFilesToFolder() {
        wireframes = new Folder("Wireframes");
        prototypes = new Folder("Prototypes");
        mockUp = new File("Mockup.png");

        designDocuments.addStore(wireframes);
        designDocuments.addStore(prototypes);
        designDocuments.addStore(mockUp);
    }

    private void addFoldersAndFilesToSubfolder() {
        initialDrafts = new Folder("Inital Drafts");
        blueprints = new File("Blueprints.docx");

        wireframes.addStore(initialDrafts);
        wireframes.addStore(blueprints);
    }

    @Test
    public void childItemsInheritPermissionFromParentDriveTest() {
        googleDrive.grantPermission(Jordan, Role.CONTRIBUTOR);

        Assert.assertEquals(Role.CONTRIBUTOR, designDocuments.getPermission(Jordan));
        Assert.assertEquals(Role.CONTRIBUTOR, developmentFiles.getPermission(Jordan));
        Assert.assertEquals(Role.CONTRIBUTOR, marketingMaterials.getPermission(Jordan));
    }

    @Test
    public void driveCannotInheritPermissionFromChildItemTest() {
        designDocuments.grantPermission(Jordan, Role.ADMIN);

        Assert.assertEquals(null, googleDrive.getPermission(Jordan));
    }

    @Test
    public void grandchildItemInheritPermissionFromDriveTest() {
        googleDrive.grantPermission(Jordan, Role.CONTRIBUTOR);

        Assert.assertEquals(Role.CONTRIBUTOR, wireframes.getPermission(Jordan));
    }

    @Test
    public void driveCannotInheritPermissionFromGrandchildItemTest() {
        wireframes.grantPermission(Jordan, Role.READER);

        Assert.assertEquals(null, googleDrive.getPermission(Jordan));
    }

    @Test
    public void childItemsInheritPermissionFromFolderTest() {
        designDocuments.grantPermission(Jordan, Role.ADMIN);

        Assert.assertEquals(Role.ADMIN, wireframes.getPermission(Jordan));
        Assert.assertEquals(Role.ADMIN, prototypes.getPermission(Jordan));
        Assert.assertEquals(Role.ADMIN, mockUp.getPermission(Jordan));
    }

    @Test
    public void folderCannotInheritPermissionFromChildItemTest() {
        wireframes.grantPermission(Jordan, Role.CONTRIBUTOR);

        Assert.assertEquals(null, designDocuments.getPermission(Jordan));
    }

    @Test
    public void grandchildItemInheritPermissionFromFolderTest() {
        designDocuments.grantPermission(Jordan, Role.CONTRIBUTOR);

        Assert.assertEquals(Role.CONTRIBUTOR, initialDrafts.getPermission(Jordan));
        Assert.assertEquals(Role.CONTRIBUTOR, blueprints.getPermission(Jordan));
    }

    @Test
    public void folderCannotInheritPermissionFromGrandchildItemTest() {
        initialDrafts.grantPermission(Jordan, Role.CONTRIBUTOR);

        Assert.assertEquals(null, designDocuments.getPermission(Jordan));
    }

    @Test
    public void permissionPropagationThroughHierarchyTest() {
        googleDrive.grantPermission(Taylor, Role.READER);

        Assert.assertEquals(Role.READER, designDocuments.getPermission(Taylor));
        Assert.assertEquals(Role.READER, developmentFiles.getPermission(Taylor));
        Assert.assertEquals(Role.READER, marketingMaterials.getPermission(Taylor));

        Assert.assertEquals(Role.READER, wireframes.getPermission(Taylor));
        Assert.assertEquals(Role.READER, prototypes.getPermission(Taylor));
        Assert.assertEquals(Role.READER, mockUp.getPermission(Taylor));

        Assert.assertEquals(Role.READER, initialDrafts.getPermission(Taylor));
        Assert.assertEquals(Role.READER, blueprints.getPermission(Taylor));        
    }

    @Test
    public void childItemOverrideInheritedPermissionTest() {
        googleDrive.grantPermission(Taylor, Role.READER);
        designDocuments.grantPermission(Taylor, Role.ADMIN);

        Assert.assertEquals(Role.ADMIN, designDocuments.getPermission(Taylor));
    }

    @Test
    public void grandChildItemOverrideInheritedPermissionTest() {
        googleDrive.grantPermission(Taylor, Role.READER);

        // wireframes is child of designDocuments so it's a grandchild of googleDrive
        wireframes.grantPermission(Taylor, Role.ADMIN);

        Assert.assertEquals(Role.ADMIN, wireframes.getPermission(Taylor));
    }
}
