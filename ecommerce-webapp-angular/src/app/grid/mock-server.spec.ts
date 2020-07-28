import { MockServer } from './mock-server';

describe('MockServer', () => {
  it('should create an instance', () => {
    expect(new MockServer()).toBeTruthy();
  });
});
