package Tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import roles.Role;
import models.Drive;
import models.File;
import models.Folder;
import models.User;

public class PermissionInheritanceTest {

    private User Sinh, Tam, Huy;
    private Drive oneDrive, googleDrive;

    private Folder designDocuments, developmentFiles;
    private File marketingMaterials;

    private Folder wireframe, prototypes;
    private File mockUp;

    private Folder initialDrafts;
    private File blueprints;

    @Before
    public void setUpUserDrive() {
        addDrivesToUser();
        addFoldersAndFilesToDrive();
        addFoldersAndFilesToFolder();
        addFoldersAndFilesToSubfolder();
    }

    private void addDrivesToUser() {
        Sinh = new User("Sinh");
        Tam = new User("Tam");
        Huy = new User("Huy");

        googleDrive = new Drive("Google Drive", Sinh);
        oneDrive = new Drive("One Drive", Sinh);

        Sinh.addDrive(googleDrive);
        Sinh.addDrive(oneDrive);
    }

    private void addFoldersAndFilesToDrive() {
        designDocuments = new Folder("Design Documents", Sinh);
        developmentFiles = new Folder("Development Files", Sinh);
        marketingMaterials = new File("Marketing Materials.pdf", Sinh, "Marketing content");

        googleDrive.addChildStore(designDocuments);
        googleDrive.addChildStore(developmentFiles);
        googleDrive.addChildStore(marketingMaterials);
    }

    private void addFoldersAndFilesToFolder() {
        wireframe = new Folder("Wireframe", Tam);
        prototypes = new Folder("Prototypes", Tam);
        mockUp = new File("Mockup.png",Tam, "Mockup content");

        designDocuments.addChildStore(wireframe);
        designDocuments.addChildStore(prototypes);
        designDocuments.addChildStore(mockUp);
    }

    private void addFoldersAndFilesToSubfolder() {
        initialDrafts = new Folder("Initial Drafts", Huy);
        blueprints = new File("Blueprints.docx", Huy, "Blueprints content");

        wireframe.addChildStore(initialDrafts);
        wireframe.addChildStore(blueprints);
    }

    @Test
    public void childItemsInheritPermissionFromParentDriveTest() {
        Sinh.grantPermission(Tam, googleDrive, Role.READER);

        Assert.assertEquals(Role.READER, designDocuments.checkPermission(Tam));
        Assert.assertEquals(Role.READER, developmentFiles.checkPermission(Tam));
        Assert.assertEquals(Role.READER, marketingMaterials.checkPermission(Tam));
    }

    @Test
    public void driveCannotInheritPermissionFromChildItemTest() {
        Sinh.grantPermission(Tam, designDocuments, Role.READER);
        Assert.assertNotEquals(Role.READER, googleDrive.checkPermission(Tam));
    }

    @Test
    public void grandchildItemInheritPermissionFromDriveTest() {

        Sinh.grantPermission(Huy, googleDrive, Role.CONTRIBUTOR);
        Assert.assertEquals(Role.CONTRIBUTOR, wireframe.checkPermission(Huy));
    }

    @Test
    public void driveCannotInheritPermissionFromGrandchildItemTest() {
        Sinh.grantPermission(Tam, googleDrive, Role.ADMIN);
        Assert.assertEquals(Role.ADMIN, googleDrive.checkPermission(Tam));
    }

    @Test
    public void childItemsInheritPermissionFromFolderTest() {
        
        Sinh.grantPermission(Huy, designDocuments, Role.READER);
        Assert.assertEquals(Role.READER, wireframe.checkPermission(Huy));
        Assert.assertEquals(Role.READER, prototypes.checkPermission(Huy));
        Assert.assertEquals(Role.READER, mockUp.checkPermission(Huy));
    }

    @Test
    public void folderCannotInheritPermissionFromChildItemTest() {
        
        Assert.assertEquals(Role.ADMIN, initialDrafts.checkPermission(Tam));
    }

    @Test
    public void grandchildItemInheritPermissionFromFolderTest() {

        Assert.assertEquals(Role.ADMIN, initialDrafts.checkPermission(Tam));
        Assert.assertEquals(Role.ADMIN, blueprints.checkPermission(Tam));
    }

    @Test
    public void folderCannotInheritPermissionFromGrandchildItemTest() {
        Huy.grantPermission(Tam, initialDrafts, Role.READER);
        Assert.assertEquals(Role.ADMIN, initialDrafts.checkPermission(Tam));
    }

    @Test
    public void permissionPropagationThroughHierarchyTest() {
        Sinh.grantPermission(Huy, googleDrive, Role.READER);
        Assert.assertEquals(Role.READER, designDocuments.checkPermission(Huy));
        Assert.assertEquals(Role.READER, developmentFiles.checkPermission(Huy));
        Assert.assertEquals(Role.READER, marketingMaterials.checkPermission(Huy));

        Assert.assertEquals(Role.READER, wireframe.checkPermission(Huy));
        Assert.assertEquals(Role.READER, prototypes.checkPermission(Huy));
        Assert.assertEquals(Role.READER, mockUp.checkPermission(Huy));

        Assert.assertEquals(Role.ADMIN, initialDrafts.checkPermission(Huy));
        Assert.assertEquals(Role.ADMIN, blueprints.checkPermission(Huy));        
    }

    @Test
    public void childItemOverrideInheritedPermissionTest() {

        Sinh.grantPermission(Huy, googleDrive, Role.CONTRIBUTOR);

        Sinh.grantPermission(Huy, designDocuments, Role.ADMIN);
        Assert.assertNotEquals(Role.READER, googleDrive.checkPermission(Huy));
        Assert.assertEquals(Role.CONTRIBUTOR, designDocuments.checkPermission(Huy));
    }

    @Test
    public void grandChildItemOverrideInheritedPermissionTest() {
        Sinh.revokePermission(Huy, googleDrive);
        // wireframe is child of designDocuments so it's a grandchild of googleDrive

        Tam.revokePermission(Huy, wireframe);
        Assert.assertNotEquals(Role.CONTRIBUTOR, googleDrive.checkPermission(Huy));
        Assert.assertNotEquals(Role.ADMIN, wireframe.checkPermission(Huy));
    }
}