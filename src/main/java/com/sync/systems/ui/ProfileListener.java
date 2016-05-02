package com.sync.systems.ui;

import java.io.IOException;
import java.util.List;

public interface ProfileListener<T> {
  public void onProfileUpdate(List<T> locations) throws IOException;
}
