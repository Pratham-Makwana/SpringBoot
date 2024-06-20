package com.pratham.SpringIntro.controller;

import com.pratham.SpringIntro.entity.SpringEntry;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/journal")
public class EntryController {

    // For act as table(add,remove)
    private Map<Long, SpringEntry> springEntries = new HashMap<>();

    @GetMapping
    public List<SpringEntry> getAll() {
        return new ArrayList<>(springEntries.values());
    }

    @PostMapping
    public boolean currentAdd(@RequestBody SpringEntry myEntry) {
        springEntries.put(myEntry.getId(), myEntry);
        return true;
    }

    @GetMapping("id/{myId}")
    public SpringEntry getSpringEntryById(@PathVariable Long myId) {
        return springEntries.get(myId);
    }

    @DeleteMapping("id/{id}")
    public SpringEntry deleteSpringEntryByID(@PathVariable Long id) {
        return springEntries.remove(id);
    }


    @PutMapping("id/{id}")
    public SpringEntry updateSpringEntryById(@PathVariable Long id, @RequestBody SpringEntry myEntry) {
            return springEntries.put(id,myEntry);
    }
}

