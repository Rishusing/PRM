@GetMapping("/releases/edit/{id}")
public String showEditForm(@PathVariable("id") String id, Model model) {
    ProductionReleaseManagement release = service.getReleaseById(id);  // Fetch existing release data
    model.addAttribute("release", release);  // Add the release data to the model
    return "create-release";  // Reuse create-release.html for editing
}

@PostMapping("/releases/edit/{id}")
public String updateRelease(@PathVariable("id") String id, @ModelAttribute("release") ProductionReleaseManagement updatedRelease) {
    // Retrieve the existing release
    ProductionReleaseManagement existingRelease = service.getReleaseById(id);
    
    // Update the fields of the existing release with the new data
    existingRelease.setPaymentsChangeNumber(updatedRelease.getPaymentsChangeNumber());
    existingRelease.setReferenceSnowCrNumber(updatedRelease.getReferenceSnowCrNumber());
    existingRelease.setSnowCrStatus(updatedRelease.getSnowCrStatus());
    existingRelease.setCreatedDate(updatedRelease.getCreatedDate());
    existingRelease.setImplementationDate(updatedRelease.getImplementationDate());
    existingRelease.setPriority(updatedRelease.getPriority());
    existingRelease.setTypeOfChange(updatedRelease.getTypeOfChange());
    existingRelease.setChangesDescription(updatedRelease.getChangesDescription());
    existingRelease.setJustification(updatedRelease.getJustification());
    existingRelease.setImpactIfNotDeployed(updatedRelease.getImpactIfNotDeployed());
    existingRelease.setOutcomeAfterChanges(updatedRelease.getOutcomeAfterChanges());
    existingRelease.setChangeInitiator(updatedRelease.getChangeInitiator());
    existingRelease.setApplicationName(updatedRelease.getApplicationName());
    existingRelease.setReviewerComment(updatedRelease.getReviewerComment());
    existingRelease.setApproverName(updatedRelease.getApproverName());
    existingRelease.setApproverDecision(updatedRelease.getApproverDecision());
    
    // Save the updated release back to the database
    service.saveRelease(existingRelease);
    
    return "redirect:/releases";  // Redirect to the list after updating
}
